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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author naber
 */
public class MainScreenController implements Initializable {
    
    @FXML
    private void openAddPart(ActionEvent event) throws IOException {
        Parent loadAdd = FXMLLoader.load(getClass().getResource("/inventory/gui/AddPart.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loadAdd,400,400));
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
