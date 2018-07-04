/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.gui;

import inventory.models.Inhouse;
import inventory.models.Outsourced;
import inventory.models.Part;
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
        modPartLblNmId.setText("Company Name");
    }
    
    @FXML
    private void modOutsourcedPart() {
        modPartOutSourcedRadio.setSelected(true);
        modPartInHouseRadio.setSelected(false);
        modPartLblNmId.setText("Machine ID");
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
        
        partId.setText(Integer.toString(part.getPartID()));
        partInv.setText(Integer.toString(part.getInStock()));
        partMax.setText(Integer.toString(part.getMax()));
        partMin.setText(Integer.toString(part.getMin()));
        partName.setText(part.getName());
        partPrice.setText(Double.toString(part.getPrice()));
    }
    
    @FXML
    private void closeModPart(ActionEvent event) throws IOException {
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
        // TODO
    }    
    
}
