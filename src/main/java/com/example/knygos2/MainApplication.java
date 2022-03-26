package com.example.knygos2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loginView = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(loginView.load(), 600, 400);
        stage.setTitle("Prisijungimo langas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}