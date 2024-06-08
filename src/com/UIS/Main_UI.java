package com.UIS;

import com.Games.board;
import com.User.user;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main_UI extends UI{

    private user user;
    final private double glow_level = 0.1;
    board board = new board();
    public Main_UI(user user)
    {
        this.user= user;
    }
    public Main_UI()
    {
        this.user=new user("访客","123456");
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("连连看");
        System.out.println(user.getUsername());
        Scene scene;

        setWidth(1200);
        setHeight(getWidth()/16*9);

        Image image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\bg2.png"));
        BackgroundImage backgroundImage = set_background(image);

        Label Title = new Label("连连看");
        Title.setTextFill(Color.WHITE);
        Title.setFont(Font.font("锐字李林哥特体简-闪", FontWeight.NORMAL,90));
        //HBox title_box = getHBox(Pos.CENTER,Title);

        Button Start = new Button("Start");
        Start.setStyle("-fx-background-color: #ff7a1e;"+"-fx-background-radius: 13;"+"-fx-text-fill: antiquewhite;"+"-fx-font-family: 'Microsoft YaHei UI';"+"-fx-font-size: 35");
        HBox Start_box = getHBox(Pos.CENTER,Start);

        Button RankList = new Button("RankList");
        RankList.setStyle("-fx-background-color: #5096ff;"+"-fx-background-radius: 13;"+"-fx-text-fill: antiquewhite;"+"-fx-font-family: 'Microsoft YaHei UI';"+"-fx-font-size: 35");
        HBox Rank_box = getHBox(Pos.CENTER,RankList);
        Rank_box.setPadding(new Insets(0,0,0,10));
        RankList.setOnMouseExited(e->
                RankList.setEffect(new Glow(0)));
        RankList.setOnMouseMoved(e->
                RankList.setEffect(new Glow(glow_level)));

        Button Tut = new Button("Tutorial");
        Tut.setStyle("-fx-background-color: #ffba24;"+"-fx-background-radius: 13;"+"-fx-text-fill: antiquewhite;"+"-fx-font-family: 'Microsoft YaHei UI';"+"-fx-font-size: 35");
        HBox Tut_box = getHBox(Pos.CENTER,Tut);
        Tut_box.setPadding(new Insets(0,0,0,10));
        Tut.setOnMouseMoved(e->
                Tut.setEffect(new Glow(glow_level)));
        Tut.setOnMouseExited(e->
                Tut.setEffect(new Glow(0)));

        Button Exit = new Button("Exit");
        Exit.setStyle("-fx-background-color: #0cb4ff;"+"-fx-background-radius: 13;"+"-fx-text-fill: antiquewhite;"+"-fx-font-family: 'Microsoft YaHei UI';"+"-fx-font-size: 35");
        HBox Exit_box = getHBox(Pos.CENTER,Exit);
        Exit_box.setPadding(new Insets(0,0,0,10));
        Exit.setOnMouseMoved(e->
                Exit.setEffect(new Glow(glow_level)));
        Exit.setOnMouseExited(e->
                Exit.setEffect(new Glow(0)));


        final GridPane gridPane = new GridPane();
        gridPane.setBackground(new Background(backgroundImage));
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setVgap(5);

        gridPane.add(Title,0,17);
        gridPane.add(Start_box,0,27);
        gridPane.add(Rank_box,0,32);
        gridPane.add(Tut_box,0,37);
        gridPane.add(Exit_box,0,42);

        scene =new Scene(gridPane,getWidth(),getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();

        Start.setOnMouseExited(e->
                Start.setEffect(new Glow(0)));
        Start.setOnMouseMoved(e->
                Start.setEffect(new Glow(glow_level)));
        Start.setOnAction(e->
        {
            GridPane gridPane1  =new GridPane();
            gridPane1.setBackground(new Background(backgroundImage));
            gridPane1.setVgap(5);
            gridPane1.setAlignment(Pos.TOP_CENTER);
            Button _8 = new Button("8x8");
            _8.setStyle("-fx-background-color: #5ed3ff;"+"-fx-background-radius: 30;"+"-fx-text-fill: antiquewhite;"+"-fx-font-family: 'Microsoft YaHei UI';"+"-fx-font-size: 40");
            HBox _8box = getHBox(Pos.CENTER,_8);
            _8.setOnMouseMoved(e1->
                    _8.setEffect(new Glow(glow_level)));
            _8.setOnMouseExited(e1->
                    _8.setEffect(new Glow(0)));

            Button _16 = new Button("16x16");
            _16.setStyle("-fx-background-color: #1489ff;"+"-fx-background-radius: 30;"+"-fx-text-fill: antiquewhite;"+"-fx-font-family: 'Microsoft YaHei UI';"+"-fx-font-size: 40");
            HBox _16box = getHBox(Pos.CENTER,_16);
            _16.setOnMouseMoved(e1->
                    _16.setEffect(new Glow(glow_level)));
            _16.setOnMouseExited(e1->
                    _16.setEffect(new Glow(0)));

            Label Title1 = new Label("连连看");
            Title1.setTextFill(Color.WHITE);
            Title1.setFont(Font.font("锐字李林哥特体简-闪", FontWeight.NORMAL,90));
            HBox title_box1 = getHBox(Pos.CENTER,Title1);

            Button Esc = new Button("Esc");
            Esc.setStyle("-fx-background-color: #ffa22c;"+"-fx-background-radius: 30;"+"-fx-text-fill: antiquewhite;"+"-fx-font-family: 'Microsoft YaHei UI';"+"-fx-font-size: 40");
            HBox Esc_box = getHBox(Pos.TOP_CENTER,Esc);
            Esc.setOnMouseMoved(e1->
                    Esc.setEffect(new Glow(glow_level)));
            Esc.setOnMouseExited(e1->
                    Esc.setEffect(new Glow(0)));
            gridPane1.add(title_box1,0,17);
            gridPane1.add(_8box,0,28);
            gridPane1.add(_16box,0,34);
            gridPane1.add(Esc_box,0,40);
            scene.setRoot(gridPane1);
            _8.setOnAction(ex->
            {
                board_Init(8);
                GridPane gridPane2 ;
                try {
                    gridPane2 = board.Init_pane();
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
                Button Esc1 = new Button("Esc");
                Esc1.setStyle("-fx-background-color: rgb(26,155,236);"+"-fx-background-radius: 12;"+"-fx-text-fill: antiquewhite;"+"-fx-font-family: 'Microsoft YaHei UI';"+"-fx-font-size: 30");
                Esc1.setTranslateY(50);
                Esc1.setOnAction(e1->
                {
                    board.setTotalTime(0);
                    scene.setRoot(gridPane1);
                    if(board.isFinished())
                    {
                        write_score("D:\\IDEASPACE\\Project1\\Ranklist\\Ranklist.txt");
                    }
                });
                HBox Esc_box1 = new HBox(Esc1);
                Esc_box1.setPadding(new Insets(120,0,0,100));

                gridPane2.add(Esc_box1,0,3);
                scene.setRoot(gridPane2);
            });
            _16.setOnAction(ex->
            {
                board_Init(16);
                GridPane gridPane2 ;
                try {
                    gridPane2 = board.Init_pane();
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
                Button Esc1 = new Button("Esc");
                Esc1.setStyle("-fx-background-color: rgb(26,155,236);"+"-fx-background-radius: 12;"+"-fx-text-fill: antiquewhite;"+"-fx-font-family: 'Microsoft YaHei UI';"+"-fx-font-size: 30");
                Esc1.setTranslateY(50);
                Esc1.setOnAction(e1->
                {
                    scene.setRoot(gridPane1);
                    board.setTotalTime(0);
                    if(board.isFinished())
                    {
                        System.out.println(1);

                        write_score("D:\\IDEASPACE\\Project1\\Ranklist\\Ranklist.txt");
                    }
                });
                HBox Esc_box1 = new HBox(Esc1);
                Esc_box1.setPadding(new Insets(120,0,0,100));

                gridPane2.add(Esc_box1,0,3);
                scene.setRoot(gridPane2);

            });
            Esc.setOnAction(ex->
                    scene.setRoot(gridPane));
            scene.setOnKeyPressed(ex->
            {
                if(ex.getCode().getName().equals("Esc"))
                {
                    board.setTotalTime(0);
                    scene.setRoot(gridPane);
                }
            });

        });
        RankList.setOnAction(e->
        {
            int row = 1;
            Label rank = new Label("Rank");
            rank.setTextFill(Color.WHITE);
            rank.setFont(Font.font("Leelawadee UI",FontWeight.NORMAL,80));
            rank.setEffect(new DropShadow(4,Color.GRAY));
            HBox rank_box = getHBox(Pos.CENTER,rank);
            rank_box.setTranslateX(-25);

            Button Esc = new Button("< Esc");
            Esc.setStyle("-fx-background-color: rgb(150,150,150,0);"+"-fx-background-radius: 12;"+"-fx-text-fill: rgb(255,255,255);"+"-fx-font-family: 'Microsoft YaHei UI';"+"-fx-font-size: 30");
            Esc.setOnAction(e1->
                    scene.setRoot(gridPane));
            HBox Esc_box = getHBox(Pos.CENTER,Esc);
            Esc_box.setTranslateX(-360);
            Esc_box.setTranslateY(0);

            GridPane gridPane1 = new GridPane();
            gridPane1.setBackground(new Background(backgroundImage));
            gridPane1.setAlignment(Pos.TOP_CENTER);
            gridPane1.setHgap(5);
            gridPane1.setVgap(5);

            gridPane1.setPadding(new Insets(30,50,0,0));
            gridPane1.add(rank_box,1,2);
            gridPane1.add(Esc_box,0,0);
            String pathname = "D:\\IDEASPACE\\Project1\\Ranklist\\Ranklist.txt";
            File Rank = new File(pathname);
            if(!Rank.exists()) {
                try {
                    boolean isFileCreated = Rank.createNewFile();
                    if (isFileCreated) {
                        System.out.println("File created: " + Rank.getName());
                    } else {
                        System.out.println("File already exists.");
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }else
            {
                try (BufferedReader reader = new BufferedReader(new FileReader(Rank))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if(line.indexOf(':')==-1)continue;
                        Label rk = new Label();
                        rk.setText(row +".");
                        rk.setTextFill(Color.WHITE);
                        rk.setFont(Font.font("Nirmala UI",FontWeight.NORMAL,40));
                        Label name = new Label(line.substring(0,line.indexOf(':')));
                        name.setTextFill(Color.WHITE);
                        name.setFont(Font.font("Nirmala UI",FontWeight.NORMAL,40));
                        Label sc =  new Label(line.substring(line.indexOf(':')+1));
                        sc.setTextFill(Color.WHITE);
                        sc.setFont(Font.font("Nirmala UI",FontWeight.NORMAL,40));
                        gridPane1.add(rk,0,row+4);
                        gridPane1.add(name,1,row+4);
                        gridPane1.add(sc,3,row+4);
                        System.out.println(line.indexOf(':'));
                        if(row>=5)break;
                        row++;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            scene.setRoot(gridPane1);
        });
        Tut.setOnAction(e->
        {
            Label Tutorial = new Label("Tutorial");
            Tutorial.setTextFill(Color.WHITE);
            Tutorial.setFont(Font.font("Leelawadee UI",FontWeight.NORMAL,80));
            Tutorial.setEffect(new DropShadow(4,Color.GRAY));
            HBox Tutorial_box = getHBox(Pos.CENTER,Tutorial);
            Tutorial_box.setTranslateY(-20);
            Tutorial_box.setTranslateX(-40);

            Label edu = new Label("    In this game,you are given 100s for 8x8 mode and 600s for 16x16 mode.\n" +
                    "    During the game,you need to find two same icons and click to match them as soon as possible." +
                    "After you match a pair of icons, you can get 1 score." +
                    "If you can match all icons , you would win this game,or the game is over." +
                    "Also if you have difficulty matching the icons,you are allowed to click the help button to get a hint."+
                    "If you are not satisfied with your performance or for any other reason ,you can click reset button to reset the game.\n"+
                    "     After you finish the game,your score will be recorded in the ranklist." +
                    "Only the top-5 players are on exhibited in the ranklist." +
                    "So try your best to get as many scores as possible in the game.\n" +
                    "     Wish you good luck and have fun.");
            edu.setWrapText(true);
            edu.setMaxWidth(600);
            edu.setTextFill(Color.WHITE);
            edu.setFont(Font.font("Yu Gothic UI",FontWeight.NORMAL,20));
            HBox edu_box = getHBox(Pos.CENTER,edu);
            edu_box.setTranslateX(-40);
            StackPane stk = new StackPane();
            Rectangle rectangle = new Rectangle(660,400);
            rectangle.setTranslateX(-40);
            rectangle.setArcWidth(20);
            rectangle.setArcHeight(20);
            rectangle.setFill(Color.rgb(150,150,150,0.2));
            rectangle.setEffect(new InnerShadow(1,Color.rgb(255,255,255,0.4)));
            stk.getChildren().add(rectangle);
            stk.getChildren().add(edu_box);
            stk.setTranslateX(10);

            Button Esc = new Button("< Esc");
            Esc.setStyle("-fx-background-color: rgb(150,150,150,0);"+"-fx-background-radius: 12;"+"-fx-text-fill: rgb(255,255,255);"+"-fx-font-family: 'Microsoft YaHei UI';"+"-fx-font-size: 30");
            Esc.setOnAction(e1->
                    scene.setRoot(gridPane));
            HBox Esc_box = getHBox(Pos.CENTER,Esc);
            Esc_box.setTranslateX(-160);
            Esc_box.setTranslateY(0);

            GridPane gridPane1 = new GridPane();
            gridPane1.setBackground(new Background(backgroundImage));
            gridPane1.setAlignment(Pos.TOP_CENTER);
            gridPane1.setHgap(5);
            gridPane1.setVgap(5);

            gridPane1.setPadding(new Insets(30,50,0,0));
            gridPane1.add(Tutorial_box,1,2);
            gridPane1.add(Esc_box,0,0);
            gridPane1.add(stk,1,4);
            scene.setRoot(gridPane1);
        });
        Exit.setOnAction(e->
                primaryStage.close());
        scene.setOnKeyPressed(ex->
        {
            if(ex.getCode().getName().equals("Esc"))
            {
                board.setTotalTime(0);
                scene.setRoot(gridPane);
            }
        });
    }
    private void write_score(String pathname) {

        System.out.println("is writing");
        System.out.println(user.getUsername());
        File Rank = new File(pathname);
        if(!Rank.exists()) {
            try {
                boolean isFileCreated = Rank.createNewFile();
                if (isFileCreated) {
                    System.out.println("File created: " + Rank.getName());
                } else {
                    System.out.println("File already exists.");
                }

            } catch (IOException exz) {
                exz.printStackTrace();
            }
        }else
        {
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(Rank))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                line = user.getUsername()+":"+board.getScore().toString();
                lines.add(line);
                lines.remove(" ");
                lines.sort((a,b)->
                {
                    int vala = Integer.parseInt(a.substring(a.indexOf(':')+1));
                    int valb = Integer.parseInt(b.substring(b.indexOf(':')+1));
                    return vala-valb;
                });

                lines = lines.reversed();
                for(String it:lines)
                {
                    System.out.println(it);
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathname))) {
                    for (String line1 : lines) {
                        writer.write(line1);
                        writer.newLine();
                    }
                }
            } catch (IOException exz) {
                exz.printStackTrace();
            }
        }
    }

    private void board_Init(int size) {
        board.setSize(size);
        board.Init();
        board.Init_g();
    }
}
