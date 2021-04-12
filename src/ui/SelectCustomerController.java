package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Customer;
import model.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.function.Predicate;

public class SelectCustomerController {

    @FXML
    private TextField filter;
    @FXML
    private TableView<Customer> personsTable;
    @FXML
    private TableColumn<Customer, String> namesCol;
    @FXML
    private TableColumn<Customer, String> lastNamesCol;
    @FXML
    private TableColumn<Customer, Long> idCol;

    private Customer selectedCustomer;

    private List<Customer> customersList;

    public SelectCustomerController(List<Customer> pl){
        customersList = pl;
    }

    public Customer display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("select-person.fxml"));
        loader.setController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.getIcons().add(new Image("file:resources/icon.png"));
        setTableView();
        stage.showAndWait();
        return selectedCustomer;
    }

    public void setTableView(){
        namesCol.setCellValueFactory(new PropertyValueFactory<>("names"));
        lastNamesCol.setCellValueFactory(new PropertyValueFactory<>("lastNames"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
        ObservableList<Customer> data = FXCollections.observableArrayList();
        data.addAll(customersList);
        FilteredList<Customer> filteredData = new FilteredList<>(data, p -> true);
        filter.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Customer ->{
                if (newValue == null || newValue.isEmpty()){
                    return true;
                } else if (Customer.getNames().toLowerCase().contains(newValue.toLowerCase())){
                    return true;
                } else if (Customer.getLastNames().toLowerCase().contains(newValue.toLowerCase())){
                    return true;
                } else return Long.toString(Customer.getIdNumber()).contains(newValue);
            });
        }));
        personsTable.setRowFactory( e -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    selectedCustomer = row.getItem();
                    namesCol.getTableView().getScene().getWindow().hide();
                }
            });
            return row;
        });
        personsTable.setItems(filteredData);
    }



}
