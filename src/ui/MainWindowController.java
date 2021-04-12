package ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.FinalProduct;
import model.BaseProduct;
import model.ProductType;
import model.Restaurant;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainWindowController {

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
        rt.getProducts().add(new BaseProduct("arroz", new ProductType("principal"), null));
        rt.getProducts().add(new BaseProduct("arroz", new ProductType("principal"), null));
        rt.getProducts().get(0).getVariations().add(new FinalProduct("personal",200.0,rt.getProducts().get(0).getName()));
        rt.getProducts().get(0).getVariations().add(new FinalProduct("familiar",200.0, rt.getProducts().get(0).getName()));
        clockThread();
        banner.setImage(new Image("file:resources/banner.png"));
    }

    @FXML
    public void manageAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("create-order.fxml"));
        loader.setController(new CreateOrderController(rt));
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



}
