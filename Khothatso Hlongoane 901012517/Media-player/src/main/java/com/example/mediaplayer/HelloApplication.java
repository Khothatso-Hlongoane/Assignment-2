package com.example.mediaplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("wyatt.fxml"));
        String style = getClass().getResource("stylesheet.css").toExternalForm();
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        scene.getStylesheets().add(style);
        stage.setTitle("Wyatt Media Player");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}