package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SetQuantityController {

    @FXML
    private Label error;
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
        stage.setResizable(false);
        stage.showAndWait();
        return quantity;
    }

    @FXML
    public void getQuantity(ActionEvent event) {
        try {
            quantity = Integer.parseInt(quantityTextField.getText());
            quantityTextField.getScene().getWindow().hide();
        } catch (NumberFormatException nfe){
            error.setText("Por favor ingrese una cantidad válida");
            nfe.printStackTrace();
        }
    }
}
