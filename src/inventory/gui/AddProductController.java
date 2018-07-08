/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.gui;

import inventory.models.Inventory;
import inventory.models.Part;
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
 * FXML Controller class
 *
 * @author naber
 */
public class AddProductController implements Initializable {
    
    @FXML
    private Button closeAddProductBtn;
    @FXML
    private TableView partsToAddTable;
    @FXML
    private TableColumn<Part, Integer> columnAddPartId;
    @FXML
    private TableColumn<Part, String> columnAddPartName;
    @FXML
    private TableColumn<Part, Integer> columnAddPartInv;
    @FXML
    private TableColumn<Part, Double> columnAddPartPrice;
    
    @FXML
    private void updateAddPartsTable() {
        partsToAddTable.setItems(Inventory.getAllParts());
    }
    
    @FXML
    private void closeAddProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainScreen.fxml"));
        Parent mainPageParent = loader.load();
        Scene mainPageScene = new Scene(mainPageParent);
        Stage eventStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        eventStage.setScene(mainPageScene);
        eventStage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        columnAddPartPrice.setCellFactory(tc -> new TableCell<Part, Double>() {

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
        columnAddPartId.setCellValueFactory(value -> value.getValue().getPropertyPartID().asObject());
        columnAddPartInv.setCellValueFactory(value -> value.getValue().getPropertyInStock().asObject());
        columnAddPartName.setCellValueFactory(value -> value.getValue().getPropertyName());
        columnAddPartPrice.setCellValueFactory(value -> value.getValue().getPropertyPrice().asObject());
        updateAddPartsTable();
    }    
    
}
