package control;

import javafx.scene.control.Alert;

public class OpenControl {
    public static void MapError(int index)
    {
        switch (index)
        {
            case 0:
                Alert nomap = new Alert(Alert.AlertType.ERROR);
                nomap.setTitle("Startup Alert");
                nomap.setHeaderText("Error: No Map Detected");
                nomap.setContentText("Please select a Map first");
                nomap.showAndWait();
                break;

            case 1:
                Alert wrongfile = new Alert(Alert.AlertType.ERROR);
                wrongfile.setTitle("Startup Alert");
                wrongfile.setHeaderText("Error: File Invalid");
                wrongfile.setContentText("Please choose a .csv map file");
                wrongfile.showAndWait();
        }

    }
}
