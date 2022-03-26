package com.example.knygos2.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    //---------------------Įrašo paieška pagal id---------------------
    public static Book searchById(String id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "SELECT * FROM `knygos` WHERE `id` = ?";

        ArrayList<Book> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { //Kol turime sarase elementus
                list.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("page"),
                        resultSet.getString("category"),
                        resultSet.getString("autor"),
                        resultSet.getString("summary")
                ));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            // Ieskome pagal pirma sarase esanti id, nes id unikalus
            return list.get(0);
        }
        // Nepavyko rasti pagal id
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    //---------------------Įrašo paieška pagal title---------------------
    public static List<Book> searchByName(String name) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String uzklausa = "SELECT * FROM `filmai` WHERE title = ?";

        List<Book> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(uzklausa);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("page"),
                        resultSet.getString("category"),
                        resultSet.getString("autor"),
                        resultSet.getString("summary")
                ));
            }
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


}