/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.gui;

import inventory.models.Inhouse;
import inventory.models.Inventory;
import inventory.models.Outsourced;
import inventory.models.Part;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author naber
 */
public class ModifyPartController implements Initializable {
    @FXML
    private RadioButton modPartOutSourcedRadio;
    @FXML
    private RadioButton modPartInHouseRadio;
    @FXML
    private TextField partId;
    @FXML
    private TextField partName;
    @FXML
    private TextField partInv;
    @FXML
    private TextField partPrice;
    @FXML
    private TextField partMax;
    @FXML
    private TextField partMin;
    @FXML
    private Label modPartLblNmId;
    @FXML
    private TextField modPartFieldNmId;
    @FXML
    private Button closeModPartBtn;
    
    @FXML
    private void modInHousePart() {
        modPartOutSourcedRadio.setSelected(false);
        modPartInHouseRadio.setSelected(true);
        modPartLblNmId.setText("Machine ID");
    }
    
    @FXML
    private void modOutsourcedPart() {
        modPartOutSourcedRadio.setSelected(true);
        modPartInHouseRadio.setSelected(false);
        modPartLblNmId.setText("Company Name");
    }
    
    @FXML
    void openModPart(Part part) {
        if (part instanceof Inhouse) {
            modInHousePart();
            modPartFieldNmId.setText(Integer.toString(((Inhouse) part).getMachineID()));
        }
        else {
            modOutsourcedPart();
            modPartFieldNmId.setText(((Outsourced) part).getCompanyName());
        }
        
        String price = formatPrice(part.getPrice());
        
        partId.setText(Integer.toString(part.getPartID()));
        partInv.setText(Integer.toString(part.getInStock()));
        partMax.setText(Integer.toString(part.getMax()));
        partMin.setText(Integer.toString(part.getMin()));
        partName.setText(part.getName());
        partPrice.setText(price);
    }
    
    @FXML
    private void saveModPart(ActionEvent event) throws IOException {
        String partNameField = partName.getText();
        int partIdField = Integer.parseInt(partId.getText());
        int partInvField = Integer.parseInt(partInv.getText());
        int partMaxField = Integer.parseInt(partMax.getText());
        int partMinField = Integer.parseInt(partMin.getText());
        double partPriceField = Double.parseDouble(partPrice.getText());
        
        if (modPartInHouseRadio.isSelected()) {
            int partMachineId = Integer.parseInt(modPartFieldNmId.getText());
            Inhouse inHousePart = new Inhouse();
            inHousePart.setInStock(partInvField);
            inHousePart.setMachineID(partMachineId);
            inHousePart.setMax(partMaxField);
            inHousePart.setMin(partMinField);
            inHousePart.setName(partNameField);
            inHousePart.setPartID(partIdField);
            inHousePart.setPrice(partPriceField);
            Inventory.updatePart(partIdField, inHousePart);
        }
        else {
            String outsourcedName = modPartFieldNmId.getText();
            Outsourced outsourcedPart = new Outsourced();
            outsourcedPart.setCompanyName(outsourcedName);
            outsourcedPart.setInStock(partInvField);
            outsourcedPart.setMax(partMaxField);
            outsourcedPart.setMin(partMinField);
            outsourcedPart.setName(partNameField);
            outsourcedPart.setPartID(partIdField);
            outsourcedPart.setPrice(partPriceField);
            Inventory.updatePart(partIdField, outsourcedPart);
        }
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainScreen.fxml"));
        Parent mainPageParent = loader.load();
        Scene mainPageScene = new Scene(mainPageParent);
        Stage eventStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        eventStage.setScene(mainPageScene);
        eventStage.show();
    }
    
    @FXML
    private void closeModPart(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Part Cancel Confirmation");
        alert.setHeaderText("Confirm cancel of modification");
        alert.setContentText("Are you sure you want to cancel modifying?");
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
        // TODO
    }    

    private String formatPrice(Double price) {
        NumberFormat formatter = new DecimalFormat("#0.00");     
        return formatter.format(price);
    }
    
}
