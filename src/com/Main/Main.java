package com.Main;

import com.UIS.LoginUI;
import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
        try {
            loginUI.start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
