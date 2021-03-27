package view;

import application.Main;
import control.OpenControl;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.File;

public class OpenView {
    @FXML private Button btn_begin;
    @FXML private ImageView img_begin;
    @FXML AnchorPane anc_open;
    @FXML Button btn_map;
    @FXML Label lbl_map;

    private Main main;
    private OpenControl oc;

    public void initialize ()
    {
        makeFadeIn();
        main = new Main();
        oc = new OpenControl();
        final FileChooser fileChooser = new FileChooser();
        Desktop desktop = Desktop.getDesktop();
        final Stage stage = new Stage();
        if (main.getMailman().getMapFile().equals(""))
        lbl_map.setText("No Map Path Selected! Enter your Map to start the simulation!");
        else
            lbl_map.setText(main.getMailman().getMapFile());

        img_begin.setImage(new Image(getClass().getResourceAsStream("/images/MailmanOpening.jpg")));


        btn_map.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = fileChooser.showOpenDialog(stage);
                if (file != null && file.getAbsolutePath().endsWith(".csv")) {
                    openFile(file);
                }
                else
                    oc.MapError(1);

            }
            private void openFile(File file) {
               main.getMailman().setMapFile(file.getAbsolutePath());
               main.getMailman().readMap();
                lbl_map.setText(file.getAbsolutePath());
            }
        });

        btn_begin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!main.getMailman().getMapFile().equals(""))
                makeFadeOut();
                else
                    oc.MapError(0);
            }

            private void makeFadeOut() {
                FadeTransition fade = new FadeTransition();
                fade.setDuration(Duration.millis(500));
                fade.setNode(anc_open);
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            main.changeView("/view/MailmanMain.fxml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                fade.play();
            }
        });
    }
    private void makeFadeIn() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(1000));
        fade.setNode(anc_open);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
}

