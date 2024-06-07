package com.Games;

import com.Tools.MyTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

public  class board {
    private int size;
    private int time_8=50;
    private int time_16=110;
    private Button reset;
    private GridPane gridPane;
    private Label Score;
    private int now = -1;
    private int cnt = 0;
    private int prei = -1, prej = -1;
    private Integer score = 0;
    private Button help;
    private Button[][] buttons;
    private int[][] g;
    private int[] st;
    private boolean[][] is_pressed;
    private MyTimer timer;
    public board(int size)
    {
        this.size=size;
    }
    public board()
    {
    }
    public Button getReset() {
        return reset;
    }

    public Button getHelp() {
        return help;
    }

    public GridPane getGridPane() {
        return gridPane;
    }
    public Integer getScore()
    {
        return score;
    }

    public int getSize() {
        return size;
    }

    public int[][] getG() {
        return g;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public void Init()
    {
        st = new int[size*size+10];
        g = new int[size+10][size+10];
        buttons = new Button[size+10][size+10];
        is_pressed = new boolean[size+10][size+10];
    }
    public void Init_g()
    {
        for(int i = 0 ;i<getSize();i++)
        {
            for(int j = 0 ;j<getSize();j++)
            {
                int x = (int)(Math.random()*1000)%(size*size/2);
                while(st[x]>=2)
                {
                    x = (int)(Math.random()*1000)%(size*size/2);
                }
                g[i][j]=x;
                st[x]++;
            }
        }
        Arrays.fill(st, 0);
    }

    public GridPane Init_pane() throws Exception
    {

        gridPane = new GridPane();
        GridPane gridPane1 = new GridPane();

        Image image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\bg3_00000.png"));
        gridPane1.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT,0,false,Side.TOP,0,false), BackgroundSize.DEFAULT)));
        gridPane1.setVgap(0);

        Image Clock = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\秒表.png"));
        ImageView clock = new ImageView(Clock);
        clock.setFitHeight(50);
        clock.setFitWidth(50);

        if(size==8)timer = new MyTimer(time_8);
        else timer = new MyTimer(time_16);

        Label Time = new Label(timer.getLast().toString());
        Time.setTextFill(Color.WHITE);
        Time.setFont(Font.font("Yu Gothic UI Regular",FontWeight.MEDIUM,40));
        Time.setStyle("-fx-effect: dropshadow(three-pass-box, #4f4f4f, 4, 0, 0, 0);");
        HBox time_box = new HBox(clock);
        time_box.getChildren().add(Time);
        time_box.setTranslateY(90);
        time_box.setTranslateX(100);
        BooleanProperty booleanProperty = new SimpleBooleanProperty(true);
        booleanProperty.addListener((observable, oldValue, newValue) ->
        {
            Time.setText(timer.getLast().toString());
        });
        Label Score_text = new Label("Score: ");
        Score = new Label(score.toString());

        Score.setTextFill(Color.WHITE);
        Score.setStyle("-fx-effect: dropshadow(three-pass-box, #4f4f4f, 4, 0, 0, 0);");
        Score.fontProperty().bind(Score_text.fontProperty());
        Score_text.setTextFill(Color.WHITE);
        Score_text.setFont(Font.font("Leelawadee UI Semilight",FontWeight.MEDIUM,50));
        Score_text.setStyle("-fx-effect: dropshadow(three-pass-box, #4f4f4f, 4, 0, 0, 0);");
        HBox score_box = new HBox(Score_text);

        score_box.getChildren().add(Score);
        score_box.setAlignment(Pos.CENTER);
        score_box.setTranslateY(50);
        score_box.setTranslateX(500);

        reset = new Button("Reset");
        reset.setStyle("-fx-background-color: rgb(8,135,236);"+"-fx-background-radius: 12;"+"-fx-text-fill: antiquewhite;"+"-fx-font-family: 'Microsoft YaHei UI';"+"-fx-font-size: 30");
        reset.setTranslateY(-50);
        HBox reset_box = new HBox(reset);
        reset_box.setPadding(new Insets(120,0,0,100));
        reset.setOnAction(e1->
        {
            gridPane.getChildren().clear();
            if(size==8)Init_gridPane8();
            else Init_gridPane16();
            for(int i = 0 ;i<getSize();i++)Arrays.fill(is_pressed[i],false);
            game();
        });


        help = new Button("Help");
        help.setStyle("-fx-background-color: rgb(255,122,30);"+"-fx-background-radius: 12;"+"-fx-text-fill: antiquewhite;"+"-fx-font-family: 'Microsoft YaHei UI';"+"-fx-font-size: 30");
        HBox help_box = new HBox(help);
        help_box.setPadding(new Insets(120,0,0,100));

        if(size==8)Init_gridPane8();
        else Init_gridPane16();
        game();
        gridPane1.add(time_box,0,0);
        gridPane1.add(score_box,0,0);
        gridPane1.add(help_box,0,4);
        gridPane1.add(reset_box,0,5);
        gridPane1.add(gridPane,1,5);

        return gridPane1;
    }

    private void game() {

        for(int i = 0 ;i<getSize();i++)
        {
            for(int j = 0 ;j<getSize();j++)
            {
                System.out.printf("%d ",g[i][j]);
                final int jc = j;
                final int ic = i;
                buttons[i][j].setOnAction(e->
                {

                    if(!is_pressed[ic][jc]) {

                        if (cnt == 0) {
                            now = g[ic][jc] % 8;
                            cnt++;
                            prej = jc;
                            prei = ic;
                            System.out.println(prei);
                            System.out.println(prej);
                            buttons[prei][prej].setEffect(new Glow(0.6));

                        } else if (cnt == 1) {
                            System.out.println(g[ic][jc]%8);
                            System.out.println(now);

                            System.out.printf("%d %d ",ic,jc);
                            System.out.printf("%d %d\n",prei,prej);
                            if (g[ic][jc] % 8 == now &&!(prei==ic&&prej==jc)) {
                                score++;
                                Score.setText(score.toString());
                                System.out.println("Yes");
                                buttons[ic][jc].setStyle("-fx-background-color: rgba(255,255,255,0.0)");
                                buttons[ic][jc].setGraphic(null);
                                buttons[prei][prej].setStyle("-fx-background-color: rgba(255,255,255,0.0)");
                                buttons[prei][prej].setGraphic(null);
                                is_pressed[ic][jc]=true;
                                is_pressed[prei][prej]=true;
                            }
                            buttons[prei][prej].setEffect(new Glow(0));
                            prej = -1;
                            prei = -1;
                            now = -1;
                            cnt = 0;
                            System.out.printf("%d %d\n",prei,prej);
                        }

                    }

                });
            }
            System.out.println(" ");
        }
    }

    private void Init_gridPane8() {
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(0,0,200,180));
        gridPane.setTranslateY(-230);

        now = -1;
        prei = -1;
        prej = -1;
        cnt = 0;
        score = 0;
        Score.setText(score.toString());
        ColumnConstraints column = new ColumnConstraints();
// 设置列约束...
        gridPane.getColumnConstraints().add(column);

        RowConstraints row = new RowConstraints();
// 设置行约束...
        gridPane.getRowConstraints().add(row);
        for(int i = 0 ;i<getSize();i++)
        {
            for(int j = 0 ;j<getSize();j++)
            {
                Button button = new Button();
                button.setStyle("-fx-background-color: rgba(255,255,255,0);"+"-fx-min-width: 40;"+"-fx-min-height: 40;");
                Image image = null;
                if(g[i][j]%8==0)
                {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_18.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }else if(g[i][j]%8==1)
                {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_16.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }else if(g[i][j]%8==2)
                {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_20.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }else if(g[i][j]%8==3)
                {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_19.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }else if(g[i][j]%8==4)
                {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_17.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }else if(g[i][j]%8==5)
                {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_26.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }else if(g[i][j]%8==6)
                {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_28.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }else if(g[i][j]%8==7)
                {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_29.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                button.setGraphic(imageView);
                button.setEffect(new DropShadow(5,Color.rgb(140,140,140,0.5)));
                buttons[i][j]=button;
                gridPane.add(button,j,i);
            }

        }
    }
    private void Init_gridPane16() {

        gridPane.setVgap(7);
        gridPane.setHgap(7);
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(0,0,200,140));
        gridPane.setTranslateY(-260);
        now = -1;
        prei = -1;
        prej = -1;
        cnt = 0;
        score = 0;
        Score.setText(score.toString());
        ColumnConstraints column = new ColumnConstraints();
// 设置列约束...
        gridPane.getColumnConstraints().add(column);

        RowConstraints row = new RowConstraints();
// 设置行约束...
        gridPane.getRowConstraints().add(row);
        for(int i = 0 ;i<getSize();i++)
        {
            for(int j = 0 ;j<getSize();j++)
            {
                if(g[i][j]%8==0)
                {
                    Button button = new Button();
                    button.setStyle("-fx-background-color: #ff0f2b;"+"-fx-min-width: 23;"+"-fx-min-height: 23;");
                    buttons[i][j]=button;
                    gridPane.add(button,j,i);
                }else if(g[i][j]%8==1)
                {
                    Button button = new Button();
                    button.setStyle("-fx-background-color: #3cd0ff;"+"-fx-min-width: 23;"+"-fx-min-height: 23;");

                    gridPane.add(button,j,i);
                    buttons[i][j] = button;
                }else if(g[i][j]%8==2)
                {
                    Button button = new Button();
                    button.setStyle("-fx-background-color: #4b7dfc;"+"-fx-min-width: 23;"+"-fx-min-height: 23;");
                    gridPane.add(button,j,i);
                    buttons[i][j] = button;
                }else if(g[i][j]%8==3)
                {
                    Button button = new Button();
                    button.setStyle("-fx-background-color: #ffba24;"+"-fx-min-width: 23;"+"-fx-min-height: 23;");
                    gridPane.add(button,j,i);
                    buttons[i][j] = button;
                }else if(g[i][j]%8==4)
                {
                    Button button = new Button();
                    button.setStyle("-fx-background-color: #21fabc;"+"-fx-min-width: 23;"+"-fx-min-height: 23;");
                    gridPane.add(button,j,i);
                    buttons[i][j] = button;
                }else if(g[i][j]%8==5)
                {
                    Button button = new Button();
                    button.setStyle("-fx-background-color: #c34bff;"+"-fx-min-width: 23;"+"-fx-min-height: 23;");
                    gridPane.add(button,j,i);
                    buttons[i][j] = button;
                }else if(g[i][j]%8==6)
                {
                    Button button = new Button();
                    button.setStyle("-fx-background-color: #fd495a;"+"-fx-min-width: 23;"+"-fx-min-height: 23;");
                    gridPane.add(button,j,i);
                    buttons[i][j] = button;
                }else if(g[i][j]%8==7)
                {
                    Button button = new Button();
                    button.setStyle("-fx-background-color: #3fe555;"+"-fx-min-width: 23;"+"-fx-min-height: 23;");
                    gridPane.add(button,j,i);
                    buttons[i][j] = button;
                }
                GridPane.setVgrow(buttons[i][j],Priority.NEVER);
                GridPane.setHgrow(buttons[i][j],Priority.NEVER);
            }
        }
    }
}
