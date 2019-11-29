package br.ufop.brTomaz.controllers;

import br.ufop.brTomaz.application.Program;
import br.ufop.brTomaz.model.entities.Civil;
import br.ufop.brTomaz.model.entities.Marriage;
import br.ufop.brTomaz.model.entities.Wedding;
import br.ufop.brTomaz.util.Utils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MarriageController implements Initializable {
    protected static Civil civil;
    @FXML
    private JFXTextField txtCEP;

    @FXML
    private JFXTextField txtStreet;

    @FXML
    private JFXTextField txtBairro;

    @FXML
    private JFXTextField txtNumber;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXComboBox<String> cmbState;

    @FXML
    private JFXButton btnNext;

    @FXML
    private Pane contentArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void closeApp(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void backPage(MouseEvent mouseEvent) throws IOException {
        Utils.setView("/views/CivilView.fxml", contentArea);
    }

    public void registration(ActionEvent actionEvent) throws IOException {
        String CEP = txtCEP.getText();
        String street = txtStreet.getText();
        String bairro = txtBairro.getText();
        String number = txtNumber.getText();
        String city = txtCity.getText();
        String state = cmbState.getSelectionModel().getSelectedItem();

        String place = CEP + " - " + street +  " - " + bairro + " - " + number +
                " - " + city + " - " + state;

        Marriage marriage = new Marriage(place, new Date());

        Wedding wedding = new Wedding(marriage, CivilController.civil);

        Parent parent = FXMLLoader.load(getClass().getResource("/views/HomeUserView.fxml"));
        Program.stage.getScene().setRoot(parent);
    }
}
