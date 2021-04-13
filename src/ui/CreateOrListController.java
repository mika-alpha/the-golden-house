package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateOrListController {

    @FXML
    private Button createButton;
    @FXML
    private Button listButton;

    private boolean answer;

    public CreateOrListController(){
    }

    @FXML
    public boolean display() {
        FXMLLoader confirmLoader = new FXMLLoader(getClass().getResource("create-or-list.fxml"));
        confirmLoader.setController(this);
        Stage confirmStage = new Stage();
        try {
            confirmStage.setScene(new Scene(confirmLoader.load()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        createButton.setOnAction(e ->{
            answer = true;
            createButton.getScene().getWindow().hide();
        });
        listButton.setOnAction(e ->{
            answer = false;
            createButton.getScene().getWindow().hide();
        });
        confirmStage.getIcons().add(new Image("file:resources/icon.png"));
        confirmStage.setResizable(false);
        confirmStage.showAndWait();
        return answer;
    }

}
