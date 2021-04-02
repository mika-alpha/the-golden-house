package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private Pane pane;

    @FXML
    void manageAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("create-order.fxml"));
        loader.setController(this);
        pane.getChildren().add(loader.load());
    }

}
