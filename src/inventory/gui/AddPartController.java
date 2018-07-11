/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.gui;

import com.sun.deploy.util.FXLoader;
import inventory.models.Inhouse;
import inventory.models.Inventory;
import inventory.models.Outsourced;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author naber
 */
public class AddPartController implements Initializable {
    @FXML
    private RadioButton addPartOutSourcedRadio;
    @FXML
    private RadioButton addPartInHouseRadio;
    @FXML
    private Label addPartLblNmId;
    @FXML
    private TextField addPartFieldNmId;
    @FXML
    private TextField addPartNameField;
    @FXML
    private TextField addInvField;
    @FXML
    private TextField addPriceField;
    @FXML
    private TextField addMaxField;
    @FXML
    private TextField addMinField;
    @FXML
    private Button closeAddPartBtn;
    
    private int partId;
    
    @FXML
    private void addInHousePart(ActionEvent event) {
        addPartOutSourcedRadio.setSelected(false);
        addPartInHouseRadio.setSelected(true);
        addPartLblNmId.setText("Machine ID");
        addPartFieldNmId.setPromptText("Mach ID");
    }
    
    @FXML
    private void addOutsourcedPart(ActionEvent event) {
        addPartOutSourcedRadio.setSelected(true);
        addPartInHouseRadio.setSelected(false);
        addPartLblNmId.setText("Company Name");
        addPartFieldNmId.setPromptText("Comp Nm");
    }
    
    @FXML
    private void addPartSave(ActionEvent event) throws IOException {
        partId = inventory.models.Inventory.incrementPartId();
        String partName = addPartNameField.getText();
        int inventory = Integer.parseInt(addInvField.getText());
        double price = Double.parseDouble(addPriceField.getText());
        int max = Integer.parseInt(addMaxField.getText());
        int min = Integer.parseInt(addMinField.getText());
        String nameId = addPartFieldNmId.getText();
        
        if (this.addPartInHouseRadio.isSelected()) {
            Inhouse inHousePart = new Inhouse();
            inHousePart.setPartID(partId);
            inHousePart.setInStock(inventory);
            inHousePart.setPrice(price);
            inHousePart.setMax(max);
            inHousePart.setMin(min);
            inHousePart.setName(partName);
            inHousePart.setMachineID(Integer.parseInt(nameId));
            Inventory.addPart(inHousePart);
        }
        else {
            Outsourced outsourcedPart = new Outsourced();
            outsourcedPart.setPartID(partId);
            outsourcedPart.setInStock(inventory);
            outsourcedPart.setPrice(price);
            outsourcedPart.setMax(max);
            outsourcedPart.setMin(min);
            outsourcedPart.setName(partName);
            outsourcedPart.setCompanyName(nameId);
            Inventory.addPart(outsourcedPart);
        }
        
        closeAddPart(event);
    }
    
    @FXML
    private void closeAddPart(ActionEvent event) throws IOException {
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
        
    }    
    
}
