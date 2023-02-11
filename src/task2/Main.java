package task2;
import java.io.*;
import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/task_6";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Root";
    public static void main(String[] args) throws IOException {
        registerDriver();
        FileWriter writerSimple;
        writerSimple = new FileWriter("task2.txt");

        writerSimple.write("INSERT INTO users(fname, phon )  VALUES(?,?)\n" +
                "SELECT * FROM users");
        writerSimple.close();
        File file = new File("task2.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String temp;
        int count = 0;
        while ((temp = reader.readLine()) != null) {
            count += 1;
            if (count == 1) {
                setAllTable(temp,"Lora", "(050)741-44-46");
           } else {
                getAllTable(temp);
            }
      }
        reader.close();
    }

    private static void setAllTable (String InsertNew,String fname, String phon  ) {
        java.sql.Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement(InsertNew);
            statement.setString(1, fname);
            statement.setString(2, phon);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection == null) {
                    throw new AssertionError();
                } else {
                    connection.close();
                    assert statement != null;
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void getAllTable(String GET_ALL) {
        Connection connection1 ;
        PreparedStatement statement = null;
        try {
            connection1 =  DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement =  ( connection1).prepareStatement(GET_ALL);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_users");
                String name = resultSet.getString("fname");
                String phon = resultSet.getString("phon");
                System.out.println(id + " " + " " + name + " " + phon);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert connection1 != null;
                connection1.close();
                assert statement != null;
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
