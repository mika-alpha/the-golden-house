package ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainWindowController {

    @FXML
    private MenuButton userButton;

    @FXML
    private Pane pane;
    @FXML
    private Label timeLabel;
    @FXML
    private ImageView banner;

    private Restaurant rt;


    public MainWindowController(Restaurant r){
        rt = r;
    }

    public void initialize(){
        clockThread();
        banner.setImage(new Image("file:resources/banner.png"));
        userButton.setText(rt.getLoggedUser().getUsername());
    }

    @FXML
    public void manageAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("manage-window.fxml"));
        loader.setController(new ManageWindowController(rt));
        pane.getChildren().clear();
        pane.getChildren().add(loader.load());
    }

    @FXML
    public void loadCreateOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("create-order.fxml"));
        loader.setController(new CreateOrderController(rt));
        pane.getChildren().clear();
        pane.getChildren().add(loader.load());
    }

    public void clockThread(){
        new Thread(() -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception ignore) {}
                LocalDateTime ldt = LocalDateTime.now();
                final String time = formatter.format(ldt);
                Platform.runLater(() -> {
                    timeLabel.setText(time);
                });
            }
        }).start();
    }

    @FXML
    public void logOut(ActionEvent event) throws IOException {
        rt.setLoggedUser(null);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        loader.setController(new LogInController(rt));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.getIcons().add(new Image("file:resources/icon.png"));
        stage.show();
        pane.getScene().getWindow().hide();
    }



}
