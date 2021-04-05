package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.*;

import java.io.IOException;

public class FirstUserController {

    @FXML
    private TextField namesField;
    @FXML
    private TextField lastNamesField;
    @FXML
    private TextField idField;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private PasswordField checkPassField;

    private Restaurant rt;
    private Alert alert;

    public FirstUserController(Restaurant r) {
        rt = r;
        alert = new Alert(null);
    }

    @FXML
    public void registerAdmin(ActionEvent event) {
        try {
            if (!checkIfStringEmpty(passField.getText()).equals(checkIfStringEmpty(checkPassField.getText()))) {
                throw new PasswordMismatchException("The passwords don't match");
            }
            rt.getUsers().add(new User(
                    checkIfStringEmpty(userField.getText()),
                    checkIfStringEmpty(passField.getText()),
                    new Employee(checkIfStringEmpty(namesField.getText()),
                            checkIfStringEmpty(lastNamesField.getText()),
                            Long.parseLong(idField.getText()), null, null)
            ));
            rt.saveData("users");
            namesField.getScene().getWindow().hide();
            showAlert("Registro exitoso", Alert.AlertType.INFORMATION, "Tu usuario ha sido creado exitosamente, usa tus datos para ingresar.");
        } catch (NullPointerException npe) {
            showAlert("Datos invalidos", Alert.AlertType.ERROR, "Todos los campos deben ser llenados.");
        } catch (NumberFormatException nfe) {
            showAlert("Identificación inválida", Alert.AlertType.ERROR, "El campo de la identificación no puede estar vacío y debe contener numeros.");
        } catch (PasswordMismatchException pme) {
            showAlert("Datos invalidos", Alert.AlertType.ERROR, "Las contraseñas no coinciden.");
        } catch (IllegalAccessException  | IOException | NoSuchFieldException iin) {
            showAlert("Error inesperado", Alert.AlertType.ERROR, "Ha ocurrido un error inesperado, si el problema persiste comuniquese con el desarrollador");
        }
    }

    public void showAlert(String title, Alert.AlertType type, String content) {
        alert.setAlertType(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public String checkIfStringEmpty(String s){
        if (s.trim().equals("")){
            return null;
        } return s;
    }
}
