package task2;
import java.sql.*;
// дублювання виконання завдання 6, запити перенесли в окремий файл цього пакету (task2.txt)
// запити наступні:
// - INSERT INTO users(fname, phon )  VALUES(?,?)
// - SELECT * FROM users;
public class Main_task6 {
    private static final String URL = "jdbc:mysql://localhost:3306/task_6";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Root";
    private static final String INSER_NEW = "INSERT INTO users(fname, phon )  VALUES(?,?)";
    private static final String GET_ALL = "SELECT * FROM users";

    public static void main(String[] args) {
        registerDriver();
        setAllTable("Olesya", "(057)841-12-40");
        getAllTable();
    }
    private static void setAllTable (String fname, String phon  ) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement(INSER_NEW);
            statement.setString(1, fname);
            statement.setString(2, phon);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static void getAllTable() {
        Connection connection = null;
        PreparedStatement statement_01 = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement_01 = connection.prepareStatement(GET_ALL);
            ResultSet resultSet_1 = statement_01.executeQuery();

            while (resultSet_1.next()) {
                String surnames  = resultSet_1.getString("fname");
                String phone  = resultSet_1.getString("phon");
                System.out.println( surnames + " "  + phone  );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement_01.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
