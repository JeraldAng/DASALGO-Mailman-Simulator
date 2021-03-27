package control;

import application.Main;
import javafx.scene.control.Alert;
import model.Mailman;
import model.Postoffice;

public class MainControl {
    private Main main;

    public boolean PostOfficeInput(String post) {
        main = new Main();
        Alert empty = new Alert(Alert.AlertType.ERROR);
        Alert wrongpost = new Alert(Alert.AlertType.ERROR);

        if ((post != null) && !(post.isEmpty()) && !(post.trim().isEmpty())) {
            int found = 0;
            for (int i = 0; i < main.getMailman().getMap().size(); i++) // checks if office is within the map
            {
                if (main.getMailman().getMap().get(i).getLocation().equalsIgnoreCase(post)) {
                    main.getMailman().setPostOffice(new Postoffice(post));
                    main.getMailman().setStartingLoc(main.getMailman().getPostOffice().getLocation());
                    found = 1;
                }
            }
            if (found == 1)
                return true;
            else {
                wrongpost.setTitle("Post Office Alert");
                wrongpost.setHeaderText("Error: Invalid Post Office");
                wrongpost.setContentText("Please Input a Valid Post Office");
                wrongpost.showAndWait();
                return false;
            }
        } else {
            empty.setTitle("Post Office Alert");
            empty.setHeaderText("Error: No Post Office detected");
            empty.setContentText("Please Input a Valid Post Office.");
            empty.showAndWait();
        }
        return false;
    }

    public static void AddSendError(int index)
    {
        switch (index)
        {
            case 0:
                Alert add = new Alert(Alert.AlertType.ERROR);
                add.setTitle("Post Office Alert");
                add.setHeaderText("Error: No Mail detected");
                add.setContentText("Please select a Mail first");
                add.showAndWait();
                break;

            case 1:
                Alert send = new Alert(Alert.AlertType.ERROR);
                send.setTitle("Post Office Alert");
                send.setHeaderText("Error: No Mails to send");
                send.setContentText("Please add a mail first");
                send.showAndWait();
        }

    }

}
