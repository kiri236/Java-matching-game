package com.UIS;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;


public class UI extends Application {
    private double height=200;
    private double width=400;
    private String title = "default";
    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public String getTitle() {
        return title;
    }

    public HBox getHBox(Pos pos, Node... nodes)
    {
        HBox hBox = new HBox();
        hBox.getChildren().addAll(nodes);
        hBox.setAlignment(pos);
        return hBox;
    }
    public BackgroundImage set_background(Image image)
    {
        return new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT,0,false,Side.TOP,0,false), BackgroundSize.DEFAULT);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(getTitle());
        Button bu = new Button("Test");
        HBox hBox = getHBox(Pos.BASELINE_CENTER,bu);
        Scene scene = new Scene(hBox,getWidth(),getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    void warning(String text) {
        Stage warning_stage = new Stage();
        warning_stage.setTitle("Warning");
        Label warning = new Label(text);
        warning.setTextFill(Color.DARKBLUE);
        warning.setFont(Font.font("Franklin Gothic Demi", FontWeight.MEDIUM, 16));
        HBox warning_box = getHBox(Pos.CENTER, warning);
        Scene scene = new Scene(warning_box, 220, 180);
        scene.setOnKeyPressed(e->
        {
            if(e.getCode().getName().equals("Esc"))
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
    }
}
