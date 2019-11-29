package br.ufop.brTomaz.controllers;

import br.ufop.brTomaz.application.Program;
import br.ufop.brTomaz.model.dao.*;
import br.ufop.brTomaz.model.entities.*;
import br.ufop.brTomaz.util.Singleton;
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

    public void registration(ActionEvent actionEvent) throws Exception {
        String CEP = txtCEP.getText();
        String street = txtStreet.getText();
        String bairro = txtBairro.getText();
        String number = txtNumber.getText();
        String city = txtCity.getText();
        String state = cmbState.getSelectionModel().getSelectedItem();

        String place = CEP + " - " + street +  " - " + bairro + " - " + number +
                " - " + city + " - " + state;

        Marriage marriage = new Marriage(place, new Date());
        Singleton.getInstance().setMarriage(marriage);

        this.registers();
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
        personDao.insert(new Person(spouse1.getName(), spouse1.getCpf(), spouse1.getEmail(), spouse1.getSex()));
        personDao.insert(new Person(spouse2.getName(), spouse2.getCpf(), spouse2.getEmail(), spouse2.getSex()));

        personDao = DaoFactory.createPerson();
        personDao.insert(new Person(witness1.getName(), witness1.getCpf(), witness1.getEmail(), witness1.getSex()));
        personDao.insert(new Person(witness2.getName(), witness2.getCpf(), witness2.getEmail(), witness2.getSex()));

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
