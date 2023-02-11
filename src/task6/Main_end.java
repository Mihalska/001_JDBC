package task6;

import java.sql.*;
// в даному класі за допомогою динамічного prepareStatement() робимо наступні запити:
// - виборку даних з нашої таблиці;
// - видалення останнього user
// - оновлюємо дані в таблиці.
public class Main_end {
    private static final String URL = "jdbc:mysql://localhost:3306/task_6";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Root";
    private static final String GET_ALL = "SELECT * FROM users";
    private static final String DELETE = "DELETE FROM users WHERE id_users = ?";
    public static void main(String[] args) {
        registerDriver();

        Connection connection = null;

        PreparedStatement statement = null;
        PreparedStatement statement1;
        PreparedStatement statement2;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement(GET_ALL);
            statement1 = connection.prepareStatement(DELETE);
            statement2 = connection.prepareStatement(GET_ALL);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_users");
                String name = resultSet.getString("fname");
                String phon = resultSet.getString("phon");
                System.out.println(id + " " + " " + name + " " + phon );
            }
 //видалення останнього user та оновлення даних.
            statement1.setInt(1,13);
             statement1.executeUpdate();
            System.out.println("__________________________________");
// виведення оновлених даних після видалення;
            ResultSet resultSet2 = statement2.executeQuery();
            while (resultSet2.next()) {
                int id = resultSet2.getInt("id_users");
                String name = resultSet2.getString("fname");
                String phon = resultSet2.getString("phon");
                System.out.println(id + " " + " " + name + " " + phon );
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        }  finally {
            try {
                connection.close();
                statement.close();
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



