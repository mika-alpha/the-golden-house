package ui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuController {

    @FXML
    private ImageView menu;

    public MenuController(){
    }

    public void initialize(){
        menu.setImage(new Image("file:resources/menu.png"));
    }
}
