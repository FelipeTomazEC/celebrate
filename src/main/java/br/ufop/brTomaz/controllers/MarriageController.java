package br.ufop.brTomaz.controllers;

import br.ufop.brTomaz.application.Program;
import br.ufop.brTomaz.model.dao.*;
import br.ufop.brTomaz.model.entities.*;
import br.ufop.brTomaz.util.Singleton;
import br.ufop.brTomaz.util.Utils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class MarriageController implements Initializable {
    protected String date;

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
    private JFXDatePicker datePicker;

    @FXML
    private JFXComboBox<String> cmbState;

    @FXML
    private JFXButton btnNext;

    @FXML
    private Pane contentArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> observableList = FXCollections.observableArrayList(Arrays.asList(
                "AC",
                "AL",
                "AP",
                "AM",
                "BA",
                "CE",
                "DF",
                "ES",
                "GO",
                "MA",
                "MT",
                "MS",
                "MG",
                "PA",
                "PB",
                "PR",
                "PE",
                "PI",
                "RJ",
                "RN",
                "RS",
                "RO",
                "RR",
                "SC",
                "SP",
                "SE",
                "TO"));

        cmbState.setItems(observableList);
    }

    public void closeApp(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void backPage(MouseEvent mouseEvent) throws IOException {
        Utils.setView("/views/CivilView.fxml", contentArea);
    }

    public void registration(ActionEvent actionEvent) throws Exception {
        String CEP = txtCEP.getText();
        String street = txtStreet.getText();
        String bairro = txtBairro.getText();
        String number = txtNumber.getText();
        String city = txtCity.getText();
        String state = cmbState.getSelectionModel().getSelectedItem();

        date = datePicker.getValue().toString();

        String place = CEP + " - " + street +  " - " + bairro + " - " + number +
                " - " + city + " - " + state;

        Marriage marriage = new Marriage(place, new Date());
        Singleton.getInstance().setMarriage(marriage);

        this.registers();
        Program.currentUser = Singleton.getInstance().getSpouse1();
        Parent parent = FXMLLoader.load(getClass().getResource("/views/HomeUserView.fxml"));
        Program.stage.getScene().setRoot(parent);
    }

    private void registers() throws Exception {
        Wedding wedding = Singleton.getInstance().getWedding();
        Civil civil = Singleton.getInstance().getWedding().getCivil();
        Marriage marriage = Singleton.getInstance().getWedding().getMarriage();
        Spouse spouse1 = Singleton.getInstance().getWedding().getSpouse1();
        Spouse spouse2 = Singleton.getInstance().getWedding().getSpouse2();
        Person witness1 = Singleton.getInstance().getWitness1();
        Person witness2 = Singleton.getInstance().getWitness2();

        CivilDao civilDao = DaoFactory.createCivil();
        civilDao.insert(civil);

        MarriageDao marriageDao = DaoFactory.createMarriage();
        marriageDao.insert(marriage);

        PersonDao personDao = DaoFactory.createPerson();
        personDao.insert(witness1);
        personDao.insert(witness2);

        SpouseDao spouseDao = DaoFactory.createSpouse();
        spouseDao.insert(spouse1);
        spouseDao.insert(spouse2);

        WeddingDao weddingDao = DaoFactory.createWedding();
        weddingDao.insert(wedding);

        WitnessDao witnessDao = DaoFactory.createWitness();
        witnessDao.insert(witness1, wedding);
        witnessDao.insert(witness2, wedding);

    }
}
