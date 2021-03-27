package view;

import application.Main;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.awt.*;

public class MailmanEnd {
    @FXML
    AnchorPane anc_end;
    @FXML
    Button btn_play;
    @FXML Button btn_exit;
    @FXML
    ImageView img_end;
    private Main main;
    public void initialize() {
        makeFadeIn();
        main = new application.Main();

        img_end.setImage(new Image(getClass().getResourceAsStream("/images/MailmanEnd.jpg")));

        btn_exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        btn_play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.getMailman().setPostOffice(null);
                makeFadeOut();
            }

            private void makeFadeOut() {
                FadeTransition fade = new FadeTransition();
                fade.setDuration(Duration.millis(500));
                fade.setNode(anc_end);
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            main.changeView("/view/MailmanOpen.fxml");
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
        fade.setNode(anc_end);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
}
