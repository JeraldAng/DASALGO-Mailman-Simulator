package view;

import application.Main;
import control.MainControl;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Postoffice;

public class MainView {
    @FXML
    ImageView img_menu;
    @FXML
    TextField txt_postoffice;
    @FXML
    Label lbl_postoffice;
    @FXML
    Button btn_add;
    @FXML
    Button btn_enter;
    @FXML
    Button btn_check;
    @FXML
    Button btn_send;
    @FXML
    ChoiceBox cmb_mails;
    @FXML
    AnchorPane anc_main;
    @FXML Label lbl_added;

    private Main main;
    private MainControl mc;

    public void initialize() {
        makeFadeIn();

        main = new application.Main();
        mc = new control.MainControl();
        lbl_added.setVisible(false);
        ObservableList<String> mail_choices = FXCollections.observableArrayList();
        for (int i = 0; i < main.getMailman().getMap().size(); i++) {
            mail_choices.add(main.getMailman().getMap().get(i).getDestination());
        }

        img_menu.setImage(new Image(getClass().getResourceAsStream("/images/MailmanMain.jpg")));
        cmb_mails.setItems(mail_choices);
        txt_postoffice.setDisable(true);
        btn_enter.setDisable(true);
        cmb_mails.setVisible(true);

        AnimationTimer time = new AnimationTimer() {
            public void handle(long now) {
                    if (main.getMailman().getPostOffice() != null)
                        lbl_postoffice.setText(main.getMailman().getPostOffice().getLocation());
                    else {
                        lbl_postoffice.setText("No Post Office Selected");
                        txt_postoffice.setDisable(false);
                        btn_enter.setDisable(false);
                        cmb_mails.setVisible(false);
                    }
            }
        };
            time.start();

        btn_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (cmb_mails.getSelectionModel().isEmpty())
                    mc.AddSendError(0);
                else {
                    main.getMailman().addmail(cmb_mails.getSelectionModel().getSelectedItem().toString());
                    cmb_mails.getSelectionModel().clearSelection();
                            lbl_added.setVisible(true);
                }
            }
        });


        btn_send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (main.getMailman().getMails().isEmpty())
                    mc.AddSendError(1);
                else {
                    if (main.getMailman().OnlyDifferentPosts() == 0) {
                        makeFadeOut();
                        main.getMailman().sendmail();
                    }
                    else {
                        makeFadeOut2();
                        main.getMailman().setPostOffice(new Postoffice(main.getMailman().getMails().get(0).getLocation()));
                    }
                }
            }

            private void makeFadeOut() {
                FadeTransition fade = new FadeTransition();
                fade.setDuration(Duration.millis(500));
                fade.setNode(anc_main);
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            main.changeView("/view/MailmanSend.fxml");
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
                fade.setNode(anc_main);
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

        btn_check.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                makeFadeOut();
            }

            private void makeFadeOut() {
                FadeTransition fade = new FadeTransition();
                fade.setDuration(Duration.millis(500));
                fade.setNode(anc_main);
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            main.changeView("/view/MailmanBag.fxml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                fade.play();
            }
        });

        btn_enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (mc.PostOfficeInput(txt_postoffice.getText()) == true) {
                    lbl_postoffice.setText(main.getMailman().getPostOffice().getLocation());
                    txt_postoffice.clear();
                    txt_postoffice.setDisable(true);
                    btn_enter.setDisable(true);
                    cmb_mails.setVisible(true);
                }
            }
        });

    }

    private void makeFadeIn() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(1000));
        fade.setNode(anc_main);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
}


