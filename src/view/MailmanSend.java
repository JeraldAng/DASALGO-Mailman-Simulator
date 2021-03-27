package view;

import application.Main;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.awt.*;

public class MailmanSend {
    @FXML AnchorPane anc_send;
    @FXML Button btn_next;
    @FXML Label lbl_mail1;
    @FXML Label lbl_mail2;
    @FXML Label lbl_mail3;
    @FXML Label lbl_mail4;
    @FXML
    ImageView img_send;
    @FXML ImageView img_delivery;
    private Main main;

    public void initialize() {
        makeFadeIn();
        main = new Main();

        img_send.setImage(new Image(getClass().getResourceAsStream("/images/MailmanSend.jpg")));
        img_delivery.setImage(new Image(getClass().getResourceAsStream("/images/delivery.png")));

        final Timeline timeline = new Timeline();
        timeline.delayProperty().setValue(Duration.seconds(1.75));

        final KeyValue kv = new KeyValue(img_delivery.xProperty(), 120);
        final KeyFrame kf = new KeyFrame(Duration.millis(250), kv);
        timeline.getKeyFrames().add(kf);

        final KeyValue kv2 = new KeyValue(img_delivery.xProperty(), 120);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);
        timeline.getKeyFrames().add(kf2);

        final KeyValue kv3 = new KeyValue(img_delivery.xProperty(), 240);
        final KeyFrame kf3 = new KeyFrame(Duration.millis(750), kv3);
        timeline.getKeyFrames().add(kf3);

        final KeyValue kv4 = new KeyValue(img_delivery.xProperty(), 240);
        final KeyFrame kf4 = new KeyFrame(Duration.millis(1000), kv4);
        timeline.getKeyFrames().add(kf4);

        final KeyValue kv5 = new KeyValue(img_delivery.xProperty(), 360);
        final KeyFrame kf5 = new KeyFrame(Duration.millis(1250), kv5);
        timeline.getKeyFrames().add(kf5);

        final KeyValue kv6 = new KeyValue(img_delivery.xProperty(), 360);
        final KeyFrame kf6 = new KeyFrame(Duration.millis(1500), kv6);
        timeline.getKeyFrames().add(kf6);

        final KeyValue kv7 = new KeyValue(img_delivery.xProperty(), 485);
        final KeyFrame kf7 = new KeyFrame(Duration.millis(1750), kv7);
        timeline.getKeyFrames().add(kf7);

        timeline.play();


        lbl_mail2.setText("");
        lbl_mail3.setText("");
        lbl_mail4.setText("");

        lbl_mail1.setText("Sent " + main.getMailman().getSentMails().get(3) + " !");
        if (main.getMailman().getSentMails().size() > 1)
            lbl_mail2.setText("Sent " + main.getMailman().getSentMails().get(2) + " !");
        if (main.getMailman().getSentMails().size() > 2)
            lbl_mail3.setText("Sent " + main.getMailman().getSentMails().get(1) + " !");
        if (main.getMailman().getSentMails().size() > 3)
            lbl_mail4.setText("Sent " + main.getMailman().getSentMails().get(0) + " !");

        btn_next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int deletecount = 4;
                if (main.getMailman().getSentMails().size() < 4) {
                    deletecount = main.getMailman().getSentMails().size();
                }
                for (int q = 0; q < deletecount; q++)
                    main.getMailman().getSentMails().remove(0);

                if (main.getMailman().getMails().isEmpty() && main.getMailman().getSentMails().isEmpty())
                {
                    makeFadeOut();
                }
                else if (!main.getMailman().getMails().isEmpty() && main.getMailman().getSentMails().isEmpty())
                {
                    main.getMailman().getPostOffice().changepostoffice(main.getMailman().getMails().get(0).getLocation());
                    System.out.println(main.getMailman().getPostOffice().getLocation());
                    main.getMailman().setPostOffice(main.getMailman().getPostOffice());
                    makeFadeOut2();
                }
                else
                {
                    try {
                        main.changeView("/view/MailmanSend.fxml");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            private void makeFadeOut() {
                FadeTransition fade = new FadeTransition();
                fade.setDuration(Duration.millis(500));
                fade.setNode(anc_send);
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            main.changeView("/view/MailmanEnd.fxml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                fade.play();
            }
            private void makeFadeOut2() {
                FadeTransition fade = new FadeTransition();
                fade.setDuration(Duration.millis(500));
                fade.setNode(anc_send);
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
        fade.setNode(anc_send);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
}
