package ui;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.InvalidLoginException;
import model.Restaurant;
import java.io.IOException;

public class LogInController {

    @FXML
    private ImageView logInBanner;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Button logInButton;

    private Alert alert;
    private Restaurant rt;

    public LogInController(Restaurant r){
        rt = r;
        alert = new Alert(null);
    }

    public void initialize(){
        logInBanner.setImage(new Image("file:resources/front.png"));
        try {
            rt.loadData();
        } catch (IllegalAccessException | IOException | ClassNotFoundException iic){
            showAlert("Error inesperado", Alert.AlertType.ERROR, "Ha ocurrido un error al intentar cargar los datos, si el problema persiste comuniquese con el desarrollador");
            iic.printStackTrace();
        }
    }

    @FXML
    public void logIn(ActionEvent event) {
        try {
            rt.logIn(username.getText(), password.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-window.fxml"));
            loader.setController(new MainWindowController(rt));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.getIcons().add(new Image("file:resources/icon.png"));
            stage.setResizable(false);
            loggingIn(stage);
            closeRequest(stage);
        } catch (InvalidLoginException ile){
            showAlert("Credenciales inválidas", Alert.AlertType.ERROR, "El usuario o la contraseña que ingresaste son incorrectos.");
        } catch (IOException io){
            showAlert("Ha ocurrido un error inesperado", Alert.AlertType.ERROR, "Por favor intente nuevamente o reinicie el programa.");
            io.printStackTrace();
        }
    }

    @FXML
    public void recoveryHelp(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("recovery.fxml"));
            loader.setController(new RecoveryController());
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            stage.getIcons().add(new Image("file:resources/icon.png"));
            stage.show();
        } catch (IOException io){
            showAlert("Ha ocurrido un error inesperado", Alert.AlertType.ERROR, "Por favor intente nuevamente o reinicie el programa.");
            io.printStackTrace();
        }
    }

    @FXML
    public void registerFirstUser(ActionEvent event) {
        if(rt.getUsers().isEmpty()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("register-first-user.fxml"));
                loader.setController(new FirstUserController(rt));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                stage.setResizable(false);
                stage.getIcons().add(new Image("file:resources/icon.png"));
                stage.show();
            } catch (IOException io) {
                showAlert("Ha ocurrido un error inesperado", Alert.AlertType.ERROR, "Por favor intente nuevamente o reinicie el programa.");
                io.printStackTrace();
            }
        } else {
            showAlert("Acción no permitida", Alert.AlertType.WARNING, "Ya hay uno o más usuarios registrados, por favor ingrese con uno de ellos.");
        }

    }

    public void showAlert(String title, Alert.AlertType type, String content){
        alert.setAlertType(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void onEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER){
            logInButton.fire();
            event.consume();
        }
    }

    public void loggingIn(Stage stage){
        PauseTransition delay = new PauseTransition(Duration.millis(1000));
        logInButton.setDisable(true);
        username.editableProperty().set(false);
        password.editableProperty().set(false);
        delay.setOnFinished(event1 -> {
            stage.show();
            logInButton.getScene().getWindow().hide();
        });
        delay.play();
    }

    public void closeRequest(Stage stage){
        stage.setOnCloseRequest(e ->{
            e.consume();
            ConfirmBoxController confirmBox = new ConfirmBoxController();
            if (confirmBox.display()){
                System.exit(0);
            }
        });
    }



}
