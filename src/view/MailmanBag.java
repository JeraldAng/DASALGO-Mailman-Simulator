package view;

import application.Main;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.awt.*;

public class MailmanBag {
    @FXML Button btn_return;
    @FXML ListView list_mails;
    @FXML
    AnchorPane anc_bag;
    @FXML
    ImageView img_bag;

    private Main main;
    public void initialize() {
        makeFadeIn();
        main = new application.Main();

        img_bag.setImage(new Image(getClass().getResourceAsStream("/images/MailmanBag.jpg")));


        btn_return.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                makeFadeOut();
            }

                private void makeFadeOut() {
                    FadeTransition fade = new FadeTransition();
                    fade.setDuration(Duration.millis(500));
                    fade.setNode(anc_bag);
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

        for (int i = 0; i < main.getMailman().getMails().size(); i++){
                list_mails.getItems().add(main.getMailman().getMails().get(i).getSchool());
            }
    }
    private void makeFadeIn() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(1000));
        fade.setNode(anc_bag);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
}
