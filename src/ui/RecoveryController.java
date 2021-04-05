package ui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RecoveryController{
    @FXML
    private ImageView twitterImage;

    public RecoveryController(){

    }

    public void initialize(){
        twitterImage.setImage(new Image("file:resources/twitter_profile.png"));
    }

}
