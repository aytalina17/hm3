package com.example.hm3;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group group = new Group();
        Scene scene = new Scene(group, 800, 800);

        Button btn1 = new Button("Двигай меня");
        btn1.setLayoutX(0);
        btn1.setLayoutY(0);
        double btn1_sizeX = 300;
        double btn1_sizeY = 150;
        btn1.setStyle("-fx-background-color: cyan");
        btn1.setPrefSize(btn1_sizeX, btn1_sizeY);
        group.getChildren().add(btn1);

        Button btn2 = new Button("Двигай меня");
        btn2.setLayoutX(0);
        btn2.setLayoutY(200);
        double btn2_sizeX = 300;
        double btn2_sizeY = 150;
        btn2.setStyle("-fx-background-color: pink");
        btn2.setPrefSize(btn2_sizeX, btn2_sizeY);
        group.getChildren().add(btn2);

        Button btn = new Button("Посчитать");
        btn.setLayoutX(0);
        btn.setLayoutY(500);
        btn.setPrefSize(100, 40);
        group.getChildren().add(btn);

        Label label1 = new Label("Площадь пересечения:");
        label1.setLayoutY(560);
        group.getChildren().add(label1);

        Label area_value = new Label();
        area_value.setLayoutY(580);
        group.getChildren().add(area_value);

        final double[] btn1_coords = new double[2];

        btn1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn1_coords[0] = mouseEvent.getX();
                btn1_coords[1] = mouseEvent.getY();
            }
        });

        final double[] btn2_coords = new double[2];

        btn2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn2_coords[0] = mouseEvent.getX();
                btn2_coords[1] = mouseEvent.getY();
            }
        });

        double[] coords1 = new double[2];

        btn1.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                coords1[0] = mouseEvent.getSceneX() - btn1_coords[0];
                coords1[1] = mouseEvent.getSceneY() - btn1_coords[1];
                btn1.setLayoutX(coords1[0]);
                btn1.setLayoutY(coords1[1]);
            }
        });

        double[] coords2 = new double[2];
        btn2.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                coords2[0] = mouseEvent.getSceneX() - btn2_coords[0];
                coords2[1] = mouseEvent.getSceneY() - btn2_coords[1];
                btn2.setLayoutX(coords2[0]);
                btn2.setLayoutY(coords2[1]);
            }
        });

        double[] btn_1 = new double[2];
        double[] btn_2 = new double[2];

        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn_1[0] = btn1.getLayoutX();
                btn_1[1] = btn1.getLayoutY();
                btn_2[0] = btn2.getLayoutX();
                btn_2[1] = btn2.getLayoutY();
                double area = areaCalculate(btn1_sizeX, btn1_sizeY, btn2_sizeX, btn2_sizeY, btn_1, btn_2);
                area_value.setText(String.valueOf(area));
            }
        });

        stage.setScene(scene);
        stage.setTitle("Подсчет площади пересечения двух прямоугольников");
        stage.show();
    }
    public double areaCalculate(double button1sizeX, double button1sizeY, double button2sizeX, double button2sizeY, double[] coords1, double[] coords2){
        double area = 0;
        if ((Math.abs(coords1[0] - coords2[0]) > 300) || (Math.abs(coords1[1] - coords2[1]) > 150)){
            area = 0;
        } else if (coords1[0] <= coords2[0]){
            if (coords1[1] <= coords2[1]){
                area = (coords1[0] + button1sizeX - coords2[0]) * (coords1[1] + button1sizeY - coords2[1]);
            } else if (coords1[1] > coords2[1]){
                area = (coords1[0] + button1sizeX - coords2[0]) * (coords2[1] + button2sizeY - coords1[1]);
            }
        } else if (coords1[0] >= coords2[0]){
            if (coords1[1] <= coords2[1]){
                area = (coords2[0] + button2sizeX - coords1[0]) * (coords1[1] + button1sizeY - coords2[1]);
            } else if (coords1[1] > coords2[1]){
                area = (coords2[0] + button2sizeX - coords1[0]) * (coords2[1] + button2sizeY - coords1[1]);
            }
        }
        return area;
    }
    public static void main(String[] args) {
        launch();
    }
}