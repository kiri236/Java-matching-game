package com.Main;

import com.UIS.LoginUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginUI loginUI = new LoginUI();
        try {
            loginUI.start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
