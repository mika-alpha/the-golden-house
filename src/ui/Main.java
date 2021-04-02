package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Restaurant;
import java.io.*;


public class Main extends Application {

    private Restaurant rt;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private MainWindowController mwc;

    public Main() throws IllegalAccessException, IOException, ClassNotFoundException {
        mwc = new MainWindowController();
        rt = new Restaurant();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-window.fxml"));
        loader.setController(mwc);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
