package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;
import java.io.IOException;

public class CreateOrderController {

    @FXML
    private TableColumn<OrderProduct, Integer> selectedQuantity;
    @FXML
    private TableView<OrderProduct> selectedProductsTV;
    @FXML
    private TableColumn<Button, Void> deleteCol;
    @FXML
    private TableColumn<OrderProduct, String> selectedProductsCol;
    @FXML
    private TableColumn<OrderProduct, Double> priceCol;
    @FXML
    private TextField total;
    @FXML
    private TextArea commentsTextArea;
    @FXML
    private Label customerLabel;
    @FXML
    private Label employeeLabel;
    @FXML
    private TextField filter;
    @FXML
    private TableView<BaseProduct> selectProductsTV;
    @FXML
    private TableColumn<BaseProduct, String> productsCol;
    @FXML
    private TableColumn<BaseProduct, String> typesCol;
    @FXML
    private TableColumn<FinalProduct, String> sizeCol;


    private Alert alert;
    private Restaurant rt;
    private Order tempOrder;

    public CreateOrderController(Restaurant r){
        alert = new Alert(null);
        rt = r;
    }


    public void initialize(){
        tempOrder = new Order(null,null,null,rt.getLoggedUser());
        setSelectProductsTV();
    }


    @FXML
    public void openMenu(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            loader.setController(new MenuController());
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.getIcons().add(new Image("file:resources/icon.png"));
            stage.setResizable(false);
            stage.show();
        } catch (IOException io){
            showAlert("Ha ocurrido un error inesperado", Alert.AlertType.ERROR, "Por favor intente nuevamente o reinicie el programa.");
            io.printStackTrace();
        }
    }

    @FXML
    public void selectCustomer(ActionEvent event)  {
        SelectCustomerController scc = new SelectCustomerController(rt.getCustomers());
        try {
            tempOrder.setCustomer(scc.display());
            customerLabel.setText(tempOrder.getCustomer().getNames() + " " + tempOrder.getCustomer().getLastNames());
        } catch (IOException io){
            showAlert("Ha ocurrido un error inesperado", Alert.AlertType.ERROR, "Por favor intente nuevamente o reinicie el programa.");
            io.printStackTrace();
        } catch (NullPointerException ignore){}
    }

    @FXML
    public void selectEmployee(ActionEvent event) {
        SelectEmployeeController sec = new SelectEmployeeController(rt.getEmployees());
        try {
            tempOrder.setDeliveredBy(sec.display());
            employeeLabel.setText(tempOrder.getDeliveredBy().getNames() + " " + tempOrder.getDeliveredBy().getLastNames());
        } catch (IOException io){
            showAlert("Ha ocurrido un error inesperado", Alert.AlertType.ERROR, "Por favor intente nuevamente o reinicie el programa.");
            io.printStackTrace();
        } catch (NullPointerException ignore){ }
    }

    public void showAlert(String title, Alert.AlertType type, String content){
        if (alert.getOwner() == null){
            alert.initOwner(employeeLabel.getScene().getWindow());
        }
        alert.setAlertType(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void setSelectProductsTV(){
        productsCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        typesCol.setCellValueFactory(new PropertyValueFactory<>("typeName"));
        ObservableList<BaseProduct> data = FXCollections.observableArrayList();
        data.addAll(rt.getProducts());
        FilteredList<BaseProduct> filteredData = new FilteredList<>(data, p -> true);
        filter.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredData.setPredicate(BaseProduct ->{
                if (newValue == null || newValue.isEmpty()){
                    return true;
                } else if (BaseProduct.getName().toLowerCase().contains(newValue.toLowerCase())){
                    return true;
                } else return BaseProduct.getTypeName().toLowerCase().contains(newValue.toLowerCase());
            });
        }));
        selectProductsTV.setRowFactory( e -> {
            TableRow<BaseProduct> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    BaseProduct p = row.getItem();
                    SelectSizeController ssc = new SelectSizeController(p.getVariations());
                    try{
                        Integer[] sp = ssc.display();
                        if (sp[0] != null && sp[1] != null) {
                            tempOrder.getProducts().add(p);
                            tempOrder.getSelectedSp().add(sp[0]);
                            tempOrder.getQuantities().add(sp[1]);
                            setSelectedProductsTV();
                            displayTotal();
                        }
                    } catch (NullPointerException npe){
                        npe.printStackTrace();
                    } catch (IOException io){
                        showAlert("Ha ocurrido un error inesperado", Alert.AlertType.ERROR, "Por favor intente nuevamente o reinicie el programa.");
                        io.printStackTrace();
                    }
                }
            });
            return row;
        });
        selectProductsTV.setItems(filteredData);
    }

    public void setSelectedProductsTV(){
        selectedProductsCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        selectedQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ObservableList<OrderProduct> data = FXCollections.observableArrayList();
        for (int i = 0; i < tempOrder.getProducts().size(); i++){
            OrderProduct op = new OrderProduct(tempOrder.getProducts().get(i).getName(),
                    tempOrder.getProducts().get(i).getVariations().get(tempOrder.getSelectedSp().get(i)).getPrice(),
                    tempOrder.getQuantities().get(i), tempOrder.getProducts().get(i).getVariations().get(tempOrder.getSelectedSp().get(i)).getSize());
            data.add(op);
        }
        Callback<TableColumn<Button, Void>, TableCell<Button, Void>> cellFactory =
                new Callback<TableColumn<Button, Void>, TableCell<Button, Void>>() {
                    @Override
                    public TableCell<Button, Void> call(final TableColumn<Button, Void> param) {
                        return new TableCell<Button, Void>() {
                            final Button btn = new Button();{
                                btn.setGraphic(new ImageView(new Image("file:resources/remove.png")));
                                btn.setTooltip(new Tooltip("Eliminar"));
                            }
                            @Override
                            public void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        int index = getIndex();
                                        tempOrder.getProducts().remove(index);
                                        tempOrder.getSelectedSp().remove(index);
                                        tempOrder.getQuantities().remove(index);
                                        getTableView().getItems().remove(index);
                                        displayTotal();
                                    });
                                    setGraphic(btn);
                                }
                                setText(null);
                            }
                        };
                    }
                };
        deleteCol.setResizable(false);
        deleteCol.setCellFactory(cellFactory);
        selectedProductsTV.setItems(data);
    }

    @FXML
    public void createOrder(ActionEvent event) {
        String code;
        try {
            code = rt.getOrders().get(rt.getOrders().size() - 1).getCode();
        } catch (IndexOutOfBoundsException iob){
            code = "";
        }
        try {
            validateOrder();
            tempOrder.generateCode(code);
            tempOrder.setOrderDate();
            rt.getOrders().add(null);
            tempOrder.setComments(commentsTextArea.getText());
            rt.getOrders().set(rt.getOrders().size() - 1, tempOrder);
            rt.saveData("orders");
            showAlert("Orden creada", Alert.AlertType.INFORMATION, "La nueva orden con código " + tempOrder.getCode() + " ha sido creada exitosamente");
            refreshScene();
        } catch (InvalidOrderException ioe){
            showAlert("Orden inválida", Alert.AlertType.ERROR,"Por favor asegurese de elegir un empleado, cliente, y de añadir por lo menos un producto");
        } catch (IllegalAccessException | IOException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void displayTotal(){
        double sum = 0;
        for (int i = 0; i < tempOrder.getProducts().size(); i++){
            sum += tempOrder.getProducts().get(i).getVariations().get(tempOrder.getSelectedSp().get(i)).getPrice();
        }
        total.setText(String.valueOf(sum));
    }

    public void refreshScene(){
        tempOrder = new Order(null,null,null,rt.getLoggedUser());
        customerLabel.setText("Seleccione un cliente");
        employeeLabel.setText("Seleccione un empleado");
        filter.setText("");
        commentsTextArea.setText("");
        total.setText("");
        setSelectedProductsTV();
    }

    public void validateOrder() throws InvalidOrderException {
        if (tempOrder.getCustomer() == null || tempOrder.getDeliveredBy() == null || tempOrder.getProducts().isEmpty()){
            throw new InvalidOrderException("The customer, the employee or the products are null or empty");
        }
    }

}
