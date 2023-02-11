package task5;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/MyJoinsDB";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Root";

    private static final String GET_ALL = "select phone, adress  from Employees inner join    Informations on Employees_id = Informations.informations_id";
    private static final String GET_ALL1 = "select LName, phone, birthday from Informations inner join Employees on Informations.informations_id= Employees.Employees_id where status = 'Неодружений' ";
    private static final String GET_ALL2 = "select LName, phone, birthday from Salaryes inner join Informations on  Salaryes.salaryes_id = Informations.informations_id inner join Employees on Informations.informations_id= Employees.Employees_id where Salaryes.position   = 'Менеджер' ";

    public static void main(String[] args) {
        registerDriver();

        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statement1 ;
        PreparedStatement statement2 ;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement(GET_ALL);
            statement1 = connection.prepareStatement(GET_ALL1);
            statement2 = connection.prepareStatement(GET_ALL2);

            ResultSet resultSet = statement.executeQuery();
            ResultSet resultSet1 = statement1.executeQuery();
            ResultSet resultSet2 = statement2.executeQuery();

            while (resultSet.next()) {
                String adress = resultSet.getString("adress");
                String phone = resultSet.getString("phone");

                System.out.print(adress + " || ");
                System.out.println(phone);
            }
            System.out.println( "--------------------------------------------");
            while (resultSet1.next()) {
                String LName = resultSet1.getString("LName");
                String phone = resultSet1.getString("phone");
                Date birthday = resultSet1.getDate("birthday");
                System.out.print(LName + " || ");
                System.out.println(phone  + " || " + birthday );
            }
            System.out.println( "--------------------------------------------");
            while (resultSet2.next()) {
                String LName = resultSet2.getString("LName");
                String phone = resultSet2.getString("phone");
                Date birthday = resultSet2.getDate("birthday");
                System.out.print(LName + " || ");
                System.out.println(phone  + " || " + birthday );
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


