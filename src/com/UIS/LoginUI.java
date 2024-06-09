package com.UIS;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import com.User.user;
import com.User.Userdata;


import java.io.FileInputStream;

public class LoginUI extends UI {
    private user user;
    private int flag = 0;
    public static Userdata userdata;

    protected boolean check_password(int k, String password) {
        user user1 = userdata.get_info(k);
        return user1.getPassword().equals(password);
    }

    public int getFlag() {
        return flag;
    }

    protected void userdata_Init() {
        userdata = new Userdata();
        userdata.Init();
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {


        primaryStage.setTitle("Login");
        Image image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\bg_00000.png"));
        userdata_Init();
        BackgroundImage backgroundImage = set_background(image);

        Label Title = new Label("Welcome");
        HBox title_box = getHBox(Pos.CENTER, Title);
        Title.setTextFill(Color.WHITE);
        Title.setFont(Font.font("Stay Wildy Personal Use Only", FontWeight.MEDIUM, 60));
        title_box.setPadding(new Insets(0, 0, 0, 8));

        Label Username = new Label("username: ");
        Username.setTextFill(Color.WHITE);
        Username.setFont(Font.font("Futura LT Condensed Light", FontWeight.NORMAL, 15));
        TextField UsernameInput = new TextField();
        UsernameInput.setPromptText("Input your username");
        HBox username_box = getHBox(Pos.CENTER, Username, UsernameInput);
        username_box.setPadding(new Insets(0, 50, 0, 0));

        Label password = new Label("password: ");
        PasswordField passwordField = new PasswordField();
        password.setTextFill(Color.WHITE);
        password.setFont(Font.font("Futura LT Condensed Light", FontWeight.NORMAL, 15));
        passwordField.setPromptText("Input your password");
        HBox password_box = getHBox(Pos.CENTER, password, passwordField);
        password_box.setPadding(new Insets(0, 50, 0, 0));

        Button login = new Button("Log in");
        Button signup = new Button("Sign up");
        login.setFont(Font.font("Futura LT Condensed Light", FontWeight.NORMAL, 12));
        signup.setFont(Font.font("Futura LT Condensed Light", FontWeight.NORMAL, 12));

        login.setOnAction(e ->
        {
            boolean success = Login(UsernameInput, passwordField);
            if (this.flag == 1) {
                Main_UI mainUi = new Main_UI(user);

                try {
                    mainUi.start(new Stage());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                primaryStage.close();
            }
        });
        SignupUI signupUI = new SignupUI();
        signup.setOnAction(e ->
        {
            try {
                try {
                    signupUI.start(new Stage());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                user = signupUI.getUser1();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        HBox button_box = getHBox(Pos.CENTER, login);
        button_box.setSpacing(30);
        button_box.getChildren().add(signup);
        button_box.setPadding(new Insets(0, 0, 0, 18));

        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(10);
        gridPane.setBackground(new Background(backgroundImage));
        gridPane.setAlignment(Pos.TOP_CENTER);

        gridPane.add(title_box, 0, 32);
        gridPane.add(username_box, 0, 36);
        gridPane.add(password_box, 0, 38);
        gridPane.add(button_box, 0, 42);

        Scene scene = new Scene(gridPane, 960, 540);
        scene.setOnKeyPressed(e ->
        {
            if (e.getCode().getName().equals("Enter")) {
                Login(UsernameInput, passwordField);
            }
            if (this.flag == 1) {
                Main_UI mainUi = new Main_UI(user);
                try {
                    mainUi.start(new Stage());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                primaryStage.close();
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private boolean Login(TextField UsernameInput, PasswordField passwordField) {
        String username = UsernameInput.getText();
        String password1 = passwordField.getText();
        if (!username.isEmpty() && !password1.isEmpty()) {
            int k = userdata.find(new user(username, password1));
            if (k == -1) {
                warning("User Not Found");
                return false;
            } else {
                if (check_password(k, password1)) {
                    flag = 1;
                    user = new user(username, password1);
                } else {
                    warning("Wrong password");
                }
            }
        } else {
            if (username.isEmpty()) {
                super.warning("Please input your username");
            } else if (password1.isEmpty()) {
                warning("Please input your password");
            }
        }
        return true;
    }


}
