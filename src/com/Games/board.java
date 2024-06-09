package com.Games;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.*;
import java.util.Arrays;

public class board {
    private int size;
    private int totalTime;

    private Button reset;
    private GridPane gridPane;
    private Label Score;
    private int now = -1;
    private int cnt = 0;
    private boolean finished;
    private int prei = -1, prej = -1;
    private Integer score = 0;
    private Button help;
    private Button[][] buttons;
    private int[][] g;
    private int[] marks;
    private int[] st;
    private boolean[][] is_pressed;

    public board(int size) {
        this.size = size;
    }

    public board() {
    }

    public Button getReset() {
        return reset;
    }

    public boolean isFinished() {
        return finished;
    }

    public Button getHelp() {
        return help;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public Integer getScore() {
        return score;
    }

    public int getSize() {
        return size;
    }

    public int[][] getG() {
        return g;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public void Init() {
        st = new int[size * size + 10];
        g = new int[size + 10][size + 10];
        buttons = new Button[size + 10][size + 10];
        is_pressed = new boolean[size + 10][size + 10];
    }

    public void Init_g() {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                int x = (int) (Math.random() * 1000) % (size * size / 2);
                while (st[x] >= 2) {
                    x = (int) (Math.random() * 1000) % (size * size / 2);
                }
                g[i][j] = x;
                st[x]++;
            }
        }
        Arrays.fill(st, 0);
    }

    public GridPane Init_pane() throws Exception {

        gridPane = new GridPane();
        GridPane gridPane1 = new GridPane();
        setFinished(false);
        Image image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\bg3_00000.png"));
        gridPane1.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, false, Side.TOP, 0, false), BackgroundSize.DEFAULT)));
        gridPane1.setVgap(0);

        Image Clock = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\秒表 (1).png"));
        ImageView clock = new ImageView(Clock);
        clock.setEffect(new DropShadow(2, Color.rgb(150, 150, 150, 0.5)));
        clock.setFitHeight(50);
        clock.setFitWidth(50);

        Label Time = new Label();

        if (size == 8) {
            totalTime = 100;
            Time.setText("01:40");
        } else {
            Time.setText("10:00");
            totalTime = 600;
        }
        Time.setTextFill(Color.WHITE);
        Time.setFont(Font.font("Yu Gothic UI Regular", FontWeight.MEDIUM, 40));
        Time.setStyle("-fx-effect: dropshadow(three-pass-box, #4f4f4f, 4, 0, 0, 0);");
        HBox time_box = new HBox(clock);
        time_box.getChildren().add(Time);
        time_box.setTranslateX(100);
        time_box.setTranslateY(110);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), e -> {
                    if (score >= getSize() * getSize() / 2) {
                        setFinished(true);
                        timeline.stop();
                        gridPane.getChildren().clear();
                        Label label = new Label("Win");
                        label.setFont(Font.font("Sitka Display Bold", FontWeight.MEDIUM, 100));
                        label.setTextFill(Color.WHITE);
                        label.setEffect(new DropShadow(10, Color.GRAY));
                        HBox label_box = new HBox(label);
                        label_box.setAlignment(Pos.CENTER);
                        label_box.setTranslateY(120);
                        label_box.setTranslateX(100);
                        gridPane.getChildren().add(label_box);
                        gridPane.setAlignment(Pos.CENTER);
                    }
                    if (totalTime > 0) {
                        int minutes = totalTime / 60;
                        int seconds = totalTime % 60;
                        Time.setText(String.format("%02d:%02d", minutes, seconds));
                        totalTime--;
                    } else {
                        Time.setText("00:00");
                        setFinished(true);
                        if (score != getSize() * getSize() / 2) {
                            gridPane.getChildren().clear();
                            gridPane.getColumnConstraints().clear();
                            gridPane.getRowConstraints().clear();

                            Label label = new Label("Game" + "\n" + "Over");
                            label.setFont(Font.font("Sitka Display Bold", FontWeight.MEDIUM, 70));
                            label.setTextFill(Color.WHITE);
                            label.setEffect(new DropShadow(8, Color.GRAY));
                            HBox label_box = new HBox(label);
                            label_box.setAlignment(Pos.CENTER);
                            if (getSize() == 8) {
                                label.setText("Game Over");
                                label_box.setTranslateX(20);
                                label_box.setTranslateY(80);
                                gridPane.add(label_box, 0, 3);
                            } else {
                                label_box.setTranslateX(180);
                                label_box.setTranslateY(100);
                                gridPane.add(label_box, 0, 0);
                            }
                        }
                        timeline.stop();
                    }
                })
        );
        timeline.setCycleCount(totalTime + 1); // 设置循环次数
        timeline.play(); // 开始倒计时

        Label Score_text = new Label("Score: ");
        Score = new Label(score.toString());
        Score.setTextFill(Color.WHITE);
        Score.setStyle("-fx-effect: dropshadow(three-pass-box, #4f4f4f, 4, 0, 0, 0);");
        Score.fontProperty().bind(Score_text.fontProperty());
        Score_text.setTextFill(Color.WHITE);
        Score_text.setFont(Font.font("Leelawadee UI Semilight", FontWeight.MEDIUM, 50));
        Score_text.setStyle("-fx-effect: dropshadow(three-pass-box, #4f4f4f, 4, 0, 0, 0);");
        HBox score_box = new HBox(Score_text);

        score_box.getChildren().add(Score);
        score_box.setAlignment(Pos.CENTER);
        score_box.setTranslateY(50);
        score_box.setTranslateX(500);

        Label max_score = new Label();
        File Rank = new File("D:\\IDEASPACE\\Project1\\Ranklist\\Ranklist.txt");
        if (!Rank.exists()) {
            max_score.setText("Max Score: " + "0");
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(Rank))) {
                String line = reader.readLine();
                if (line != null) {
                    max_score.setText("Max Score: " + line.substring(line.indexOf(':') + 1));
                } else {
                    max_score.setText("Max Score: " + "0");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        max_score.setTextFill(Color.WHITE);
        max_score.setFont(Font.font("Leelawadee UI Semilight", FontWeight.NORMAL, 20));
        max_score.setStyle("-fx-effect: dropshadow(three-pass-box, #4f4f4f, 4, 0, 0, 0);");
        HBox max_score_box = new HBox(max_score);
        max_score_box.setAlignment(Pos.CENTER);
        max_score_box.setTranslateY(85);
        max_score_box.setTranslateX(500);

        reset = new Button("Reset");
        reset.setStyle("-fx-background-color: rgb(8,135,236);" + "-fx-background-radius: 12;" + "-fx-text-fill: antiquewhite;" + "-fx-font-family: 'Microsoft YaHei UI';" + "-fx-font-size: 30");
        reset.setTranslateY(-50);
        HBox reset_box = new HBox(reset);
        reset_box.setPadding(new Insets(120, 0, 0, 100));
        reset.setOnAction(e1 ->
        {
            gridPane.getChildren().clear();
            setFinished(false);
            Init_g();
            if (size == 8) Init_gridPane8();
            else Init_gridPane16();
            if (size == 8) {
                totalTime = 100;
                Time.setText("01:40");
            } else {
                Time.setText("10:00");
                totalTime = 600;
            }
            game();
        });


        help = new Button("Help");
        help.setStyle("-fx-background-color: rgb(255,122,30);" + "-fx-background-radius: 12;" + "-fx-text-fill: antiquewhite;" + "-fx-font-family: 'Microsoft YaHei UI';" + "-fx-font-size: 30");
        help.setOnAction(e ->
        {
            marks = get_help();
            buttons[marks[0]][marks[1]].setEffect(new Glow(0.3));
            buttons[marks[2]][marks[3]].setEffect(new Glow(0.3));
        });
        HBox help_box = new HBox(help);
        help_box.setPadding(new Insets(120, 0, 0, 100));

        if (size == 8) Init_gridPane8();
        else Init_gridPane16();
        game();
        gridPane1.add(time_box, 0, 0);
        gridPane1.add(score_box, 0, 0);
        gridPane1.add(max_score_box, 0, 0);
        gridPane1.add(help_box, 0, 4);
        gridPane1.add(reset_box, 0, 5);
        gridPane1.add(gridPane, 1, 5);

        return gridPane1;
    }

    private int[] get_help() {
        int[] ans = new int[]{-1, -1, -1, -1};
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (is_pressed[i][j]) continue;
                else {
                    for (int u = 0; u < getSize(); u++) {
                        for (int v = 0; v < getSize(); v++) {
                            if (is_pressed[u][v] || (u == i && v == j)) continue;
                            else {
                                if (g[u][v] % getSize() == g[i][j] % getSize()) {
                                    ans = new int[]{i, j, u, v};
                                    return ans;
                                }
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }

    private void game() {

        for (int i = 0; i < getSize(); i++) Arrays.fill(is_pressed[i], false);
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                System.out.printf("%d ", g[i][j]);
                final int jc = j;
                final int ic = i;
                buttons[i][j].setOnAction(e ->
                {

                    if (marks != null && marks[0] != -1) {
                        buttons[marks[0]][marks[1]].setEffect(new Glow(0));
                        buttons[marks[2]][marks[3]].setEffect(new Glow(0));
                        marks[0] = -1;
                    }
                    if (!is_pressed[ic][jc]) {

                        if (cnt == 0) {
                            now = g[ic][jc] % getSize();
                            cnt++;
                            prej = jc;
                            prei = ic;
                            System.out.println(prei);
                            System.out.println(prej);
                            buttons[prei][prej].setEffect(new Glow(0.6));

                        } else if (cnt == 1) {
                            System.out.println(g[ic][jc] % getSize());
                            System.out.println(now);

                            System.out.printf("%d %d ", ic, jc);
                            System.out.printf("%d %d\n", prei, prej);
                            if (g[ic][jc] % getSize() == now && !(prei == ic && prej == jc)) {
                                score++;
                                Score.setText(score.toString());
                                System.out.println("Yes");
                                buttons[ic][jc].setStyle("-fx-background-color: rgba(255,255,255,0.0)");
                                buttons[ic][jc].setGraphic(null);
                                buttons[prei][prej].setStyle("-fx-background-color: rgba(255,255,255,0.0)");
                                buttons[prei][prej].setGraphic(null);
                                is_pressed[ic][jc] = true;
                                is_pressed[prei][prej] = true;
                            }
                            buttons[prei][prej].setEffect(new Glow(0));
                            prej = -1;
                            prei = -1;
                            now = -1;
                            cnt = 0;
                            System.out.printf("%d %d\n", prei, prej);
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
        gridPane.setPadding(new Insets(0, 0, 200, 180));
        gridPane.setTranslateY(-230);
        val_init();

        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                Button button = new Button();
                button.setStyle("-fx-background-color: rgba(255,255,255,0);" + "-fx-min-width: 40;" + "-fx-min-height: 40;");
                Image image = null;
                if (g[i][j] % 8 == 0) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_18.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                } else if (g[i][j] % 8 == 1) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_16.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (g[i][j] % 8 == 2) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_20.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (g[i][j] % 8 == 3) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_19.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (g[i][j] % 8 == 4) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_17.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (g[i][j] % 8 == 5) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_26.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                } else if (g[i][j] % 8 == 6) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_28.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                } else if (g[i][j] % 8 == 7) {
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
                button.setEffect(new DropShadow(5, Color.rgb(140, 140, 140, 0.5)));
                buttons[i][j] = button;
                gridPane.add(button, j, i);
            }

        }
    }

    private void Init_gridPane16() {

        gridPane.setVgap(0);
        gridPane.setHgap(0);
        gridPane.setMaxSize(200, 200);
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setTranslateX(90);
        gridPane.setTranslateY(-270);
        val_init();

        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                Button button = new Button();
                button.setStyle("-fx-background-color: rgb(0,0,0,0)");

                Image image = null;
                if (g[i][j] % 16 == 0) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_18.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                } else if (g[i][j] % 16 == 1) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_16.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (g[i][j] % 16 == 2) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_20.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (g[i][j] % 16 == 3) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_19.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (g[i][j] % 16 == 4) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_17.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (g[i][j] % 16 == 5) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_26.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                } else if (g[i][j] % 16 == 6) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_28.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                } else if (g[i][j] % 16 == 7) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_29.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (g[i][j] % 16 == 8) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_6.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (g[i][j] % 16 == 9) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_7.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (g[i][j] % 16 == 10) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_8.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (g[i][j] % 16 == 11) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_10.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (g[i][j] % 16 == 12) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_9.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (g[i][j] % 16 == 13) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_37.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (g[i][j] % 16 == 14) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_38.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (g[i][j] % 16 == 15) {
                    try {
                        image = new Image(new FileInputStream("D:\\IDEASPACE\\Project1\\icons\\chara_icon_39.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(23);
                imageView.setFitHeight(23);
                button.setMaxSize(23, 23);
                button.setGraphic(imageView);
                button.setEffect(new DropShadow(5, Color.rgb(140, 140, 140, 0.5)));
                buttons[i][j] = button;
                gridPane.add(button, j, i);
            }

        }
    }

    private void val_init() {
        now = -1;
        prei = -1;
        prej = -1;
        cnt = 0;
        score = 0;
        Score.setText(score.toString());
        ColumnConstraints column = new ColumnConstraints();
        gridPane.getColumnConstraints().add(column);

        RowConstraints row = new RowConstraints();
        gridPane.getRowConstraints().add(row);
    }
}
