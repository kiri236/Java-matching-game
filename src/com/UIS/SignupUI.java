package com.UIS;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import com.User.user;
import javafx.util.Duration;

import java.io.FileInputStream;

public class SignupUI extends UI{
    private user user1;
    private  int flag ;
    private Stage primaryStage;
    public int getFlag() {
        return flag;
    }
    private void Init_flag()
    {
        flag = 0;
    }
    public user getUser1() {
        return user1;
    }
    private void user_Init()
    {
        user1 = new user();
    }
    public void close()
    {
        primaryStage.close();
    }
    private boolean Configure(TextField username,TextField password ,TextField password1)
    {
        return !username.getText().isEmpty()&&!password.getText().isEmpty()&&password.getText().equals(password1.getText());
    }
    public void start() throws Exception {
        primaryStage = new Stage();
        Init_flag();
        primaryStage.setTitle("Sign up");
        Image image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\bg_00000.png"));
        BackgroundImage backgroundImage = set_background(image);

        Label Title = new Label("Sign up");
        HBox title_box = getHBox(Pos.CENTER,Title);
        Title.setTextFill(Color.WHITE);
        Title.setFont(Font.font("MI LANTING", FontWeight.MEDIUM,30));
        title_box.setPadding(new Insets(0,0,0,8));

        Label Username = new Label("username: ");
        Username.setTextFill(Color.WHITE);
        Username.setFont(Font.font("Futura LT Condensed Light",FontWeight.NORMAL,15));
        TextField UsernameInput = new TextField();
        UsernameInput.setPromptText("Input your username");
        HBox username_box = getHBox(Pos.CENTER,Username,UsernameInput);
        username_box.setPadding(new Insets(0,50,0,0));

        Label password = new Label("password: ");
        PasswordField passwordField = new PasswordField();
        password.setTextFill(Color.WHITE);
        password.setFont(Font.font("Futura LT Condensed Light",FontWeight.NORMAL,15));
        passwordField.setPromptText("Input your password");
        HBox password_box = getHBox(Pos.CENTER,password,passwordField);
        password_box.setPadding(new Insets(0,50,0,0));

        Label password1 = new Label("check password: ");
        PasswordField passwordField1 = new PasswordField();
        password1.setTextFill(Color.WHITE);
        password1.setFont(Font.font("Futura LT Condensed Light",FontWeight.NORMAL,15));
        passwordField1.setPromptText("Check your password");
        HBox password_box1 = getHBox(Pos.CENTER,password1,passwordField1);
        password_box1.setPadding(new Insets(0,95,0,0));

        Button button = new Button("Sign up");
        HBox button_box = getHBox(Pos.CENTER,button);
        button_box.setPadding(new Insets(0,0,0,12));

        button.setOnAction(e->
        {
            SignUP(UsernameInput, passwordField, passwordField1);
        });

        GridPane gridPane =new GridPane();
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setBackground(new Background(backgroundImage));

        gridPane.add(title_box,0,30);
        gridPane.add(username_box,0,38);
        gridPane.add(password_box,0,42);
        gridPane.add(password_box1,0,46);
        gridPane.add(button_box,0,50);
        Scene scene = new Scene(gridPane,960,540);

        scene.setOnKeyPressed(e->
        {
            if(e.getCode().getName().equals("Enter"))
            {
                SignUP(UsernameInput, passwordField, passwordField1);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void SignUP(TextField UsernameInput, PasswordField passwordField, PasswordField passwordField1) {
        if(Configure(UsernameInput, passwordField, passwordField1))
        {
            flag = 1;
            user_Init();
            user1.setPassword(passwordField1.getText());
            user1.setUsername(UsernameInput.getText());
            if(LoginUI.userdata.find(user1)==-1)
            {
                LoginUI.userdata.add(user1);
                Stage warning_stage = new Stage();
                warning_stage.setTitle("Success");
                Label warning = new Label("Sign up successfully");
                LoginUI.userdata.finish();
                warning.setTextFill(Color.DARKBLUE);
                warning.setFont(Font.font("Franklin Gothic Demi", FontWeight.MEDIUM, 16));
                HBox warning_box = getHBox(Pos.CENTER, warning);
                Scene scene = new Scene(warning_box, 220, 180);
                scene.setOnKeyPressed(ez->
                {
                    if(ez.getCode().getName().equals("Esc"))
                    {
                        warning_stage.close();
                    }
                });
                warning_stage.setScene(scene);
                warning_stage.show();
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event ->
                {
                    warning_stage.close();
                }));
                timeline.play();

            }else
            {
                warning("This user has already existed");
            }
        }else {
            if (UsernameInput.getText().isEmpty()) {
                warning("Please input username");
            } else if (passwordField.getText().isEmpty()) {
                warning("Please input password");
            }else if(passwordField1.getText().isEmpty())
            {
                warning("Please check password");
            }else if(!passwordField1.getText().equals(passwordField.getText()))
            {
                warning("Please check password again");
            }
        }
    }


}
