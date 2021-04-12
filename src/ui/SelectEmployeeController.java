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
import model.Employee;
import java.io.IOException;
import java.util.List;

public class SelectEmployeeController {

    @FXML
    private TextField filter;
    @FXML
    private TableView<Employee> personsTable;
    @FXML
    private TableColumn<Employee, String> namesCol;
    @FXML
    private TableColumn<Employee, String> lastNamesCol;
    @FXML
    private TableColumn<Employee, Long> idCol;

    private Employee selectedEmployee;

    private List<Employee> employeesList;

    public SelectEmployeeController(List<Employee> el){
        employeesList = el;
    }

    public Employee display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("select-person.fxml"));
        loader.setController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.getIcons().add(new Image("file:resources/icon.png"));
        setTableView();
        stage.showAndWait();
        return selectedEmployee;
    }

    public void setTableView(){
        namesCol.setCellValueFactory(new PropertyValueFactory<>("names"));
        lastNamesCol.setCellValueFactory(new PropertyValueFactory<>("lastNames"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
        ObservableList<Employee> data = FXCollections.observableArrayList();
        data.addAll(employeesList);
        FilteredList<Employee> filteredData = new FilteredList<>(data, p -> true);
        filter.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Employee ->{
                if (newValue == null || newValue.isEmpty()){
                    return true;
                } else if (Employee.getNames().toLowerCase().contains(newValue.toLowerCase())){
                    return true;
                } else if (Employee.getLastNames().toLowerCase().contains(newValue.toLowerCase())){
                    return true;
                } else return Long.toString(Employee.getIdNumber()).contains(newValue);
            });
        }));
        personsTable.setRowFactory( e -> {
            TableRow<Employee> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    selectedEmployee = row.getItem();
                    namesCol.getTableView().getScene().getWindow().hide();
                }
            });
            return row;
        });
        personsTable.setItems(filteredData);
    }
}
