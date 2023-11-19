package com.example.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class weatherApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(weatherApp.class.getResource("weather.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 336, 409);
        primaryStage.setResizable(false);
        primaryStage.setTitle("weather");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args){
        launch();
    }
}
