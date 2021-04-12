package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.FinalProduct;

import java.io.IOException;
import java.util.ArrayList;

public class SelectSizeController {

    @FXML
    private TableView<FinalProduct> productsTable;

    @FXML
    private TableColumn<FinalProduct, String> sizeCol;

    @FXML
    private TableColumn<FinalProduct, Double> priceCol;

    private ArrayList<FinalProduct> variations;
    private Integer[] selection;

    public SelectSizeController(ArrayList<FinalProduct> pv){
        variations = pv;
        selection = new Integer[2];
    }

    public Integer[] display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("select-size.fxml"));
        loader.setController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.getIcons().add(new Image("file:resources/icon.png"));
        setTableView();
        stage.showAndWait();
        return selection;
    }

    public void setTableView() {
        try {
            sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            ObservableList<FinalProduct> data = FXCollections.observableArrayList();
            data.addAll(variations);
            productsTable.setRowFactory(e -> {
                TableRow<FinalProduct> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        SetQuantityController sqc = new SetQuantityController();
                        selection[0] = row.getIndex();
                        try {
                            selection[1] = sqc.display();
                        } catch (IOException io) {
                            io.printStackTrace();
                        }
                        priceCol.getTableView().getScene().getWindow().hide();
                    }
                });
                return row;
            });
            productsTable.setItems(data);
        } catch (NullPointerException npe){
            npe.printStackTrace();
        }
    }


}
