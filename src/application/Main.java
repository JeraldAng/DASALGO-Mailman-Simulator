package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import model.Mailman;

import java.util.concurrent.atomic.AtomicLong;


    public class Main extends Application {
        public static Stage window;
        public static final AtomicLong min = new AtomicLong();
        public static final AtomicLong sec = new AtomicLong();
        private static Mailman m = new Mailman();


        public static void main (String[] args) {launch(args);}

        @Override
        public void start(Stage primaryStage) throws Exception
        {
            this.window = primaryStage;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MailmanOpen.fxml"));
            Scene scene = new Scene(loader.load());

            Media bgmusic = new Media(getClass().getResource("../music/Porter Robinson - Harborside (Unreleased).mp3").toExternalForm());
            AudioClip soundtrack = new AudioClip(bgmusic.getSource());
            soundtrack.setCycleCount(100);
            soundtrack.play();

            primaryStage.setScene(scene);
            primaryStage.setResizable(true); // maybe change to false
            primaryStage.setTitle("DASALGO Mailman Simulator");
            primaryStage.show();

        }

        public void changeView (String file) throws Exception
        {
            FXMLLoader view = new FXMLLoader(getClass().getResource(file));

            Scene scene = new Scene(view.load());
            window.setScene(scene);
            window.setResizable(false);
            window.show();
        }

        public void setTitle (String title)
        {
            window.setTitle(title);
        }

        public Mailman getMailman ()
        {
            return m;
        }

        public void setMailman (Mailman m) {this.m = m;}

        public long getMin() {
            return this.min.get();
        }

        public long getSec() {
            return this.sec.get();
        }

    }

