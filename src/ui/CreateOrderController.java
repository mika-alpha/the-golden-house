package ui;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Restaurant;

import java.io.IOException;

public class CreateOrderController {

    @FXML
    private TextArea commentsTextArea;
    @FXML
    private Label customerLabel;
    @FXML
    private Label employeeLabel;

    private Alert alert;

    private Restaurant rt;


    public CreateOrderController(Restaurant r){
        alert = new Alert(null);
        rt = r;
    }


    @FXML
    void createOrder(ActionEvent event) {

    }

    @FXML
    void openMenu(ActionEvent event) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            loader.setController(new MenuController());
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.getIcons().add(new Image("file:resources/icon.png"));
            stage.setResizable(false);
            stage.show();
        } catch (IOException io){
            showAlert("Ha ocurrido un error inesperado", Alert.AlertType.ERROR, "Por favor intente nuevamente o reinicie el programa.");
            io.printStackTrace();
        }
    }

    @FXML
    void selectCustomer(ActionEvent event) {

    }

    @FXML
    void selectEmployee(ActionEvent event) {

    }

    public void showAlert(String title, Alert.AlertType type, String content){
        alert.setAlertType(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
