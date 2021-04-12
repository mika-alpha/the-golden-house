package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SetQuantityController {

    @FXML
    private TextField quantityTextField;

    private Integer quantity;

    public SetQuantityController(){

    }

    public Integer display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("set-quantity.fxml"));
        loader.setController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.getIcons().add(new Image("file:resources/icon.png"));
        stage.showAndWait();
        return quantity;
    }

    @FXML
    public void getQuantity(ActionEvent event) {
        quantity = Integer.parseInt(quantityTextField.getText());
        quantityTextField.getScene().getWindow().hide();
    }
}
