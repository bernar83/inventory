/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.gui;

import inventory.models.Part;
import inventory.models.Inventory;
import static inventory.models.Inventory.deletePart;
import static inventory.models.Inventory.deleteProduct;
import static inventory.models.Inventory.getAllParts;
import static inventory.models.Inventory.getAllProducts;
import inventory.models.Product;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.ResourceBundle;
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
    private TextField partToSearchFor;
    @FXML
    private TextField productToSearchFor;
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, Integer> columnProductId;
    @FXML
    private TableColumn<Product, String> columnProductName;
    @FXML
    private TableColumn<Product, Integer> columnProductInv;
    @FXML
    private TableColumn<Product, Double> columnProductPrice;
    
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
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyPart.fxml"));
        Parent modPartParent = loader.load();
        Scene modPartScene = new Scene(modPartParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ModifyPartController modController = loader.getController();
        modController.openModPart(selectedPart);
        mainStage.hide();
        mainStage.setScene(modPartScene);
        mainStage.show();
    }
    
    @FXML
    private void deletePartFromInventory(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Part Delete Confirmation");
        alert.setHeaderText("Confirm deletion of part");
        alert.setContentText("Are you sure you want to delete part?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
            deletePart(selectedPart);
            updatePartsTable();
        }    
    }
    
    @FXML
    private void deleteProductFromInventory() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Product Delete Confirmation");
        alert.setHeaderText("Confirm deletion of product");
        alert.setContentText("Are you sure you want to delete part?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
            deleteProduct(selectedProduct);
            updateProductsTable();
        }   
    }
    
    @FXML
    private void searchPart() {
        int searchedPartID = Integer.parseInt(partToSearchFor.getText());
        
        ObservableList<Part> searchedPart = Inventory.lookupPart(searchedPartID);
        partsTable.setItems(searchedPart);
    }
    
    @FXML
    private void searchProduct() {
        int searchedProductID = Integer.parseInt(productToSearchFor.getText());
        
        ObservableList<Product> searchedProduct = Inventory.lookupProduct(searchedProductID);
        productsTable.setItems(searchedProduct);
    }
    
    @FXML
    private void openModifyProduct(ActionEvent event) throws IOException {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyProduct.fxml"));
        Parent modPartParent = loader.load();
        Scene modPartScene = new Scene(modPartParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ModifyProductController modController = loader.getController();
        modController.openModProduct(selectedProduct);
        mainStage.hide();
        mainStage.setScene(modPartScene);
        mainStage.show();
    }
    
    @FXML
    private void openAddProduct(ActionEvent event) throws IOException {
        Parent loadAddProduct = FXMLLoader.load(getClass().getResource("/inventory/gui/AddProduct.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loadAddProduct));
        ((Node)(event.getSource())).getScene().getWindow().hide();
        stage.show();
    }
    
    @FXML
    private void updatePartsTable() {
        partsTable.setItems(getAllParts());
    }
    
    @FXML
    private void updateProductsTable() {
        productsTable.setItems(getAllProducts());
    }
    
    @FXML
    private void closeMain(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Please confirm you want to exit.");
        alert.setContentText("Any unsaved changes will be lost.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
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
        columnProductPrice.setCellFactory(tc -> new TableCell<Product, Double>() {

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
        columnProductId.setCellValueFactory(value -> value.getValue().getPropertyProductId().asObject());
        columnProductInv.setCellValueFactory(value -> value.getValue().getPropertyStock().asObject());
        columnProductName.setCellValueFactory(value -> value.getValue().getPropertyProductName());
        columnProductPrice.setCellValueFactory(value -> value.getValue().getPropertyPrice().asObject());
        updateProductsTable();
    }    
    
}
