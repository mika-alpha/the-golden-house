package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Restaurant;

public class ManageWindowController {

    @FXML
    private Button usersButton;

    private Restaurant restaurant;
    private CreateOrListController col;


    public ManageWindowController(Restaurant r) {
        col = new CreateOrListController();
        restaurant = r;
    }


    public void initialize(){
        if (restaurant.getLoggedUser().getEmployee().getCreatedBy() != null){
            usersButton.disableProperty().set(true);
        }
    }


    @FXML
    public void manageCustomers(ActionEvent event) {
        if (col.display()){

        } else {

        }
    }

    @FXML
    public void manageEmployees(ActionEvent event) {
        if (col.display()){

        } else {

        }

    }

    @FXML
    public void manageIngredients(ActionEvent event) {
        if (col.display()){

        } else {

        }

    }

    @FXML
    public void manageProducts(ActionEvent event) {
        if (col.display()){

        } else {

        }

    }

    @FXML
    public void manageTypes(ActionEvent event) {
        if (col.display()){

        } else {

        }

    }

    @FXML
    public void manageUsers(ActionEvent event) {
        if (col.display()){

        } else {

        }
    }

}

