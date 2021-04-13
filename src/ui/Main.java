package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.*;


public class Main extends Application {

    private Restaurant rt;
    private LogInController lic;

    public Main() {
        rt = new Restaurant();
        rt.getProducts().add(new BaseProduct("arroz con pollo", new ProductType("principal"), null));
        rt.getProducts().add(new BaseProduct("´omurice", new ProductType("secundario"), null));
        rt.getProducts().get(0).getVariations().add(new FinalProduct("personal",200.0));
        rt.getProducts().get(0).getVariations().add(new FinalProduct("familiar",200.0));
        rt.getProducts().get(1).getVariations().add(new FinalProduct("personal",200.0));
        rt.getCustomers().add(new Customer("Pepito Pepo","Juarez Rodriguez",112321314, null, null, "calle siempre viva","123123",""));
        rt.getCustomers().add(new Customer("Juanita","Lopez Castro",456456545, null, null, "calle nunca inviva","3453453123",""));
        rt.getEmployees().add(new Employee("Marco","Alvarez",67876876,null,null));
        rt.getEmployees().add(new Employee("Alcibiades","Pachichana",89098,null,null));
        lic = new LogInController(rt);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        loader.setController(lic);
        primaryStage.setTitle("La Casa Dorada");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:resources/icon.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
