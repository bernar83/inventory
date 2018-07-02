/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.gui;

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
    private Label modPartLblNmId;
    @FXML
    private Button closeModPartBtn;
    
    @FXML
    private void modInHousePart(ActionEvent event) {
        modPartOutSourcedRadio.setSelected(false);
        modPartInHouseRadio.setSelected(true);
        modPartLblNmId.setText("Company Name");
    }
    
    @FXML
    private void modOutsourcedPart(ActionEvent event) {
        modPartOutSourcedRadio.setSelected(true);
        modPartInHouseRadio.setSelected(false);
        modPartLblNmId.setText("Machine ID");
    }
    
    @FXML
    private void closeModPart(ActionEvent event) throws IOException {
        Parent modPartSave = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(modPartSave);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        window.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
