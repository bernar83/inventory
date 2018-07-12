/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.gui;

import inventory.models.Inventory;
import inventory.models.Part;
import inventory.models.Product;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
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
    
    private ObservableList<Part> stagedparts = FXCollections.observableArrayList();
    private int productId;
    
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
    private void saveAddProduct(ActionEvent event) throws IOException {
        productId = Inventory.incrementProductId();
        String productNameField = productName.getText();
        int productInvField = Integer.parseInt(productInv.getText());
        double productPriceField = Double.parseDouble(productPrice.getText());
        int productMaxField = Integer.parseInt(productMax.getText());
        int productMinField = Integer.parseInt(productMin.getText());
        
        Product product = new Product();
        product.setInStock(productInvField);
        product.setMax(productMaxField);
        product.setMin(productMinField);
        product.setName(productNameField);
        product.setPrice(productPriceField);
        product.setProductID(productId);
        if (stagedPartsTable.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a part from the existing list to the product"); 
            alert.showAndWait();
        }
        else {
            for (int i = 0; i < stagedPartsTable.getItems().size(); i++) {
                Part stagedPart = stagedPartsTable.getItems().get(i);
                product.addAssociatedPart(stagedPart);
            }
            Inventory.addProduct(product);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainScreen.fxml"));
            Parent mainPageParent = loader.load();
            Scene mainPageScene = new Scene(mainPageParent);
            Stage eventStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            eventStage.setScene(mainPageScene);
            eventStage.show();
        }
    }
    
    @FXML
    private void closeAddProduct(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Product Cancel Confirmation");
        alert.setHeaderText("Confirm cancel of Product");
        alert.setContentText("Are you sure you want to cancel this product?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainScreen.fxml"));
            Parent mainPageParent = loader.load();
            Scene mainPageScene = new Scene(mainPageParent);
            Stage eventStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            eventStage.setScene(mainPageScene);
            eventStage.show();
        }  
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
