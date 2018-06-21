/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

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
    private void addInHousePart(ActionEvent event) {
        addPartOutSourcedRadio.setSelected(false);
        addPartInHouseRadio.setSelected(true);
        addPartLblNmId.setText("Company Name");
        addPartFieldNmId.setPromptText("Comp Nm");
    }
    
    @FXML
    private void addOutsourcedPart(ActionEvent event) {
        addPartOutSourcedRadio.setSelected(true);
        addPartInHouseRadio.setSelected(false);
        addPartLblNmId.setText("Machine ID");
        addPartFieldNmId.setPromptText("Mach ID");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
