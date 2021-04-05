package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class ConfirmBoxController {

    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;
    @FXML
    private Label label;

    private boolean answer;

    public ConfirmBoxController(){
    }

    @FXML
    public boolean display() {
        FXMLLoader confirmLoader = new FXMLLoader(getClass().getResource("confirm-box.fxml"));
        confirmLoader.setController(this);
        Stage confirmStage = new Stage();
        try {
            confirmStage.setScene(new Scene(confirmLoader.load()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        yesButton.setOnAction(e ->{
            answer = true;
            label.getScene().getWindow().hide();
        });
        noButton.setOnAction(e ->{
            answer = false;
            label.getScene().getWindow().hide();
        });
        noButton.setDefaultButton(true);
        confirmStage.getIcons().add(new Image("file:resources/icon.png"));
        confirmStage.setResizable(false);
        confirmStage.showAndWait();
        return answer;
    }
}
