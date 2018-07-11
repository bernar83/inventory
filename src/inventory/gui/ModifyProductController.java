/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.gui;

import inventory.models.Inventory;
import static inventory.models.Inventory.getAllParts;
import inventory.models.Part;
import inventory.models.Product;
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
public class ModifyProductController implements Initializable {
    
    @FXML
    private Button closeModProductBtn;
    @FXML
    private TextField productId;
    @FXML
    private TextField productName;
    @FXML
    private TextField productInv;
    @FXML
    private TextField productPrice;
    @FXML
    private TextField productMax;
    @FXML
    private TextField productMin;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> partId;
    @FXML
    private TableColumn<Part, String> partName;
    @FXML
    private TableColumn<Part, Integer> partInv;
    @FXML
    private TableColumn<Part, Double> partPrice;
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
    @FXML
    private TextField partToSearchFor;
    
    private ObservableList<Part> stagedparts = FXCollections.observableArrayList();
    
    @FXML
    private void updatePartsTable() {
        partsTable.setItems(getAllParts());
    }
    
    @FXML
    private void updateStagedPartsTable() {
        stagedPartsTable.setItems(stagedparts);
    }
    
    @FXML
    public void openModProduct(Product product) {
        productId.setText(Integer.toString(product.getProductId()));
        productName.setText(product.getName());
        productInv.setText(Integer.toString(product.getInStock()));
        productPrice.setText(Double.toString(product.getPrice()));
        productMax.setText(Integer.toString(product.getMax()));
        productMin.setText(Integer.toString(product.getMin()));
        stagedparts = product.getParts();
        updateStagedPartsTable();
    }
    
    @FXML
    private void searchAddPart() {
        int searchedAddPartId = Integer.parseInt(partToSearchFor.getText());
        
        ObservableList<Part> searchedPart = Inventory.lookupPart(searchedAddPartId);
        partsTable.setItems(searchedPart);
    }
    
    @FXML
    private void stagePart() {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        
        stagedparts.add(selectedPart);
        stagedPartsTable.setItems(stagedparts);
    }
    
    @FXML
    private void deleteStagedPart() {
        stagedPartsTable.getItems().remove(stagedPartsTable.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    private void closeModProduct(ActionEvent event) throws IOException {
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
        partPrice.setCellFactory(tc -> new TableCell<Part, Double>() {

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
        partId.setCellValueFactory(value -> value.getValue().getPropertyPartID().asObject());
        partInv.setCellValueFactory(value -> value.getValue().getPropertyInStock().asObject());
        partName.setCellValueFactory(value -> value.getValue().getPropertyName());
        partPrice.setCellValueFactory(value -> value.getValue().getPropertyPrice().asObject());
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
        updatePartsTable();
    }    
    
}
