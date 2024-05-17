package org.example;

import org.example.Utils.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException {

        var connection = ConnectionManager.open();

        while (true){

            System.out.println("--------------------");
            System.out.println("1. Add an order.");
            System.out.println("2. View orders.");
            System.out.println("3. Take order.");
            System.out.println("--------------------");


            int scanner = new Scanner(System.in).nextInt();


            if(scanner == 1){


                String sqlOne ="insert into orders (orders, time) values (?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlOne);

                System.out.println("--------------------");
                System.out.println("Order: ");
                System.out.println("--------------------");

                String in = new Scanner(System.in).nextLine();
                preparedStatement.setString(1, in);

                Date date = new Date();
                preparedStatement.setString(2, date.toString());

                preparedStatement.executeUpdate();


            } else if (scanner == 2) {

                Statement statement = connection.createStatement();

                String sqlTwo = "select * from orders order by id desc";

                ResultSet resu = statement.executeQuery(sqlTwo);


                System.out.println("--------------------");
                while (resu.next()){
                    System.out.println(resu.getInt("id") + " || "
                            + resu.getString("orders") + " || "
                            + resu.getString("time") + " || ");
                }
                System.out.println("--------------------");


            } else if (scanner == 3) {


                String sqlThree = "select * from orders where id = ?";
                String sqlFour = "delete from orders where id = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlThree);

                System.out.println("--------------------");
                System.out.println("Take id order: ");
                System.out.println("--------------------");

                int IdTask = new Scanner(System.in).nextInt();
                preparedStatement.setInt(1, IdTask);

                preparedStatement.execute();
                ResultSet resu = preparedStatement.getResultSet();

                System.out.println("--------------------");
                while (resu.next()){

                    System.out.println(resu.getInt("id") + " || "
                            + resu.getString("orders") + " || "
                            + resu.getString("time") + " || ");

                }
                System.out.println("--------------------");

                preparedStatement = connection.prepareStatement(sqlFour);
                preparedStatement.setInt(1, IdTask);
                preparedStatement.executeUpdate();


            }else {

                System.out.println("Unknown comm, try again");

            }


        }

    }
}