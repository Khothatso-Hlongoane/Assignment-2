package com.example.mediaplayer;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class HelloController {
    @FXML
    private MediaView mediaView;
    private MediaPlayer player;
    @FXML
    private Slider duration;
    @FXML
    private Label time;
    @FXML
    private Slider volume;
    @FXML
    private Label videoName;


    @FXML
    public void initialize() {
        String video = getClass().getResource("Finnish Industry Investment with Enevo.mp4").toExternalForm();
        Media media = new Media(video);
        player = new MediaPlayer(media);

        player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                  duration.setValue(newValue.toSeconds());
            }
        });

        duration.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player.seek(Duration.seconds(duration.getValue()));
            }
        });

        duration.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player.seek(Duration.seconds(duration.getValue()));
            }
        });

        player.setOnReady(new Runnable() {
            @Override
            public void run() {
                Duration total = media.getDuration();
                duration.setMax(total.toSeconds());
            }
        });

        volume.setValue(player.getVolume() * 100);
        volume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                player.setVolume(volume.getValue()/100);
            }
        });

        mediaView.setMediaPlayer(player);
    }

    @FXML
    void playVideo(MouseEvent event) {
        player.play();
        videoName.setText("Now Playing: Finnish Industry Investment with Enevo");
    }

    @FXML
    void stopVideo(MouseEvent event) {
        player.stop();
    }

    @FXML
    void pauseVideo(MouseEvent event) {
        player.pause();
    }
}