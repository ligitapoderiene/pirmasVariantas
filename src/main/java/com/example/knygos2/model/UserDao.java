package com.example.knygos2.model;
import com.example.knygos2.model.User;
import com.example.knygos2.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {

//    public static void insert(User user){
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        session.save(user);
//        session.getTransaction().commit();
//    }


    public static void create(User user) {
        //Prisijungimai prie db
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "INSERT INTO `users`(`username`, `password`, `email`) VALUES (?, ?, ?)";

        //Vykdome prisijungimai prie db ir atliekama uzklausa
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");

            //Siekiant isvengti sql injekciju, kiekviena laukeli surasom uzklausa atskirai - ignoruojami specialus simboliai
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());

            //Naujo įrašo sukurimui, esamo iraso redagavimui ir trynimui, naudojame execute update metoda
            //Esamo iraso paieskai naudojame executeQuery metoda
            preparedStatement.executeUpdate();

            //vykdzius uzklausa gera praktika uzdaryti prisijungimus
            preparedStatement.close();
            connection.close();

            System.out.println("Pavyko sukurti nauja įrašą");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Įvyko klaida, kuriant naują įrašą");
        }
    }

    public static String getBCryptPassword(String username) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "SELECT password FROM `users` WHERE `username` = ?";

        String passwordBCrypted = "";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, username);

            //Tik įrašu paieskai executeQuery()
            // ResultSet yra RAW neapdirbti duomenys. Stulpelio pav atitinka rakta, o konkreti iraso reiksme
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) { //Kol turime sarase elementus
                passwordBCrypted = resultSet.getString("password");
            }
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passwordBCrypted;
    }
}