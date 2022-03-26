package com.example.knygos2.controler;

import com.example.knygos2.MainApplication;
import com.example.knygos2.model.UserDao;
import com.example.knygos2.utils.BCryptPassword;
import com.example.knygos2.utils.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label loginStatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    public void onLoginButtonClick() {
        String username2 = username.getText();
        String password2 = password.getText();
        if (Validation.isValidUsername(username2) && Validation.isValidPassword(password2)) {
            //loginStatus.setText("Duomenys įvesti teisingai");
            String passwordDb = UserDao.getBCryptPassword(username2);
            if (passwordDb.equals("")) {
                loginStatus.setText("Klaidingai įvestas prisijungimo vardas arba slaptažodis");
            } else {
                boolean isValidPassword = BCryptPassword.checkPassword(password2, passwordDb);
                if (isValidPassword) {
                    loginStatus.setText("Teisingai įvestas prisijungimo vardas ir slaptažodis DB");

                } else loginStatus.setText("Slaptažodis įvestas neteisingai");

            }

        } else {
            loginStatus.setText("Klaidinga įvestis");
        }
        //loginStatus.setText("Prisijungimo vardas: " + username2 + " " + ", slaptažodis: " + password2);
    }

    @FXML
    public void onRegisterButtonClick(ActionEvent event) throws IOException {
        //Vaizdo užkrovimas
        Parent root = FXMLLoader.load(MainApplication.class.getResource("register-view.fxml"));
        Stage registerStage = new Stage();
        //Stage (langas) bus vienas, scenų gali būti daug
        registerStage.setTitle("Registracijos langas");
        registerStage.setScene(new Scene(root, 600, 400));
        //Parodymas lango
        registerStage.show();
        //Kodas reikalingas paslėpti prieš tai buvusį langą
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    public void goToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("dashboard-view.fxml"));
        Stage LoginStage = new Stage();
        LoginStage.setTitle("Knygų langas");
        LoginStage.setScene(new Scene(root, 1100, 700));
        LoginStage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}