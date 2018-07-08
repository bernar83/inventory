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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextField;
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
    private TableView<Part> partsToAddTable;
    @FXML
    private TableColumn<Part, Integer> columnAddPartId;
    @FXML
    private TableColumn<Part, String> columnAddPartName;
    @FXML
    private TableColumn<Part, Integer> columnAddPartInv;
    @FXML
    private TableColumn<Part, Double> columnAddPartPrice;
    @FXML
    private Button addPartToSearchFor;
    @FXML
    private TextField partToSearchFor;
    @FXML
    private TableView<Part> stagedPartsTable;
    @FXML
    private TableColumn<Part, Integer> stagedPartId;
    @FXML
    private TableColumn<Part, String> stagedPartName;
    @FXML
    private TableColumn<Part, Integer> stagedPartInv;
    @FXML
    private TableColumn<Part, Double> stagedPartPrice;
    
    private ObservableList<Part> stagedparts = FXCollections.observableArrayList();
    
    @FXML
    private void searchAddPart() {
        int searchedAddPartId = Integer.parseInt(partToSearchFor.getText());
        
        ObservableList<Part> searchedPart = Inventory.lookupPart(searchedAddPartId);
        partsToAddTable.setItems(searchedPart);
    }
    
    @FXML
    private void stagePart() {
        Part selectedPart = partsToAddTable.getSelectionModel().getSelectedItem();
        
        stagedparts.add(selectedPart);
        stagedPartsTable.setItems(stagedparts);
    }
    
    @FXML
    private void updateAddPartsTable() {
        partsToAddTable.setItems(Inventory.getAllParts());
    }
    
    @FXML
    private void deleteStagedPart() {
        stagedPartsTable.getItems().remove(stagedPartsTable.getSelectionModel().getSelectedItem());
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
        stagedPartPrice.setCellFactory(tc -> new TableCell<Part, Double>() {

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
        stagedPartId.setCellValueFactory(value -> value.getValue().getPropertyPartID().asObject());
        stagedPartInv.setCellValueFactory(value -> value.getValue().getPropertyInStock().asObject());
        stagedPartName.setCellValueFactory(value -> value.getValue().getPropertyName());
        stagedPartPrice.setCellValueFactory(value -> value.getValue().getPropertyPrice().asObject());
        updateAddPartsTable();
    }    
    
}
