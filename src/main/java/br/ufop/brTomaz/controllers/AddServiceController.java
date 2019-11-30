package br.ufop.brTomaz.controllers;

import br.ufop.brTomaz.model.dao.DaoFactory;
import br.ufop.brTomaz.model.dao.EnterpriseDao;
import br.ufop.brTomaz.model.dao.ServiceDao;
import br.ufop.brTomaz.model.dao.impl.EnterpriseDaoJDBC;
import br.ufop.brTomaz.model.dao.impl.ServiceDaoJDBC;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.entities.Enterprise;
import br.ufop.brTomaz.model.entities.Service;
import br.ufop.brTomaz.util.Constraints;
import br.ufop.brTomaz.util.Utils;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AddServiceController implements Initializable {
    @FXML
    private Pane contentArea;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtValue;

    @FXML
    private JFXTextField txtNameEnterprise;

    @FXML
    private JFXTextField txtCEP;

    @FXML
    private JFXTextField txtCNPJ;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXTextField txtStreet;

    @FXML
    private JFXTextField txtNeighborhood;

    @FXML
    private JFXTextField txtNumber;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXComboBox<String> cmbState;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Constraints.setTextFieldDouble(txtValue);
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

    public void addService(ActionEvent actionEvent) throws IOException {
        String nameService = txtName.getText();
        String valueService = txtValue.getText();
        String nameEnterprise = txtNameEnterprise.getText();
        String CEP = txtCEP.getText();
        String CNPJ = txtCNPJ.getText();
        String street = txtStreet.getText();
        String neighborhood = txtNeighborhood.getText();
        String city = txtCity.getText();
        String number = txtNumber.getText();
        String phone = txtPhone.getText();
        String state = cmbState.getSelectionModel().getSelectedItem();

        String place = CEP + " - " + street + " - " + " - " + number + " - " +
                neighborhood + " - " + city + " - " + state;
        Enterprise enterprise = new Enterprise(CNPJ, phone, place, nameEnterprise);

        EnterpriseDao enterpriseDao = new EnterpriseDaoJDBC(DB.getConnection());
        enterpriseDao.insert(enterprise);

        Service service = new Service(nameService, Double.parseDouble(valueService), enterprise);

        ServiceDao serviceDao = new ServiceDaoJDBC(DB.getConnection());
        serviceDao.insert(service);

        Utils.setView("/views/ServicesView.fxml", contentArea);
    }

    public void closeApp(MouseEvent mouseEvent) {
        System.exit(0);
    }

}
