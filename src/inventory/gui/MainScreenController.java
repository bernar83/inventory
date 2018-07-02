/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.gui;

import inventory.models.Part;
import inventory.models.Inventory;
import static inventory.models.Inventory.getAllParts;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 *
 * @author naber
 */
public class MainScreenController implements Initializable {
    @FXML
    private Button closeMainBtn;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> columnPartId;
    @FXML
    private TableColumn<Part, String> columnPartName;
    @FXML
    private TableColumn<Part, Integer> columnPartInv;
    @FXML
    private TableColumn<Part, Double> columnPartPrice;
    
    @FXML
    private void openAddPart(ActionEvent event) throws IOException {
        Parent loadAdd = FXMLLoader.load(getClass().getResource("/inventory/gui/AddPart.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loadAdd,400,400));
        ((Node)(event.getSource())).getScene().getWindow().hide();
        stage.show();
    }
    
    @FXML
    private void openModifyPart(ActionEvent event) throws IOException {
        Parent loadModify = FXMLLoader.load(getClass().getResource("/inventory/gui/ModifyPart.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loadModify,400,400));
        ((Node)(event.getSource())).getScene().getWindow().hide();
        stage.show();
    }
    
    @FXML
    private void openModifyProduct(ActionEvent event) throws IOException {
        Parent loadModProduct = FXMLLoader.load(getClass().getResource("/inventory/gui/ModifyProduct.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loadModProduct));
        stage.show();
    }
    
    @FXML
    private void openAddProduct(ActionEvent event) throws IOException {
        Parent loadAddProduct = FXMLLoader.load(getClass().getResource("/inventory/gui/AddProduct.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loadAddProduct));
        stage.show();
    }
    
    @FXML
    private void updatePartsTable() {
        partsTable.setItems(getAllParts());
    }
    
    @FXML
    private void closeMain(ActionEvent event) {
        Stage stage = (Stage) closeMainBtn.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        columnPartPrice.setCellFactory(tc -> new TableCell<Part, Double>() {

            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(price));
                }
            }
        });
        columnPartId.setCellValueFactory(value -> value.getValue().getPropertyPartID().asObject());
        columnPartInv.setCellValueFactory(value -> value.getValue().getPropertyInStock().asObject());
        columnPartName.setCellValueFactory(value -> value.getValue().getPropertyName());
        columnPartPrice.setCellValueFactory(value -> value.getValue().getPropertyPrice().asObject());
        updatePartsTable();
    }    
    
}
