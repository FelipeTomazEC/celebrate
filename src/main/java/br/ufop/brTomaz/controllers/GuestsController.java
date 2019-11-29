package br.ufop.brTomaz.controllers;

import br.ufop.brTomaz.application.Program;
import br.ufop.brTomaz.model.dao.impl.ConvidaDaoJDBC;
import br.ufop.brTomaz.model.dao.impl.PersonDaoJDBC;
import br.ufop.brTomaz.model.dao.impl.WeddingDaoJDBC;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.entities.Person;
import br.ufop.brTomaz.model.entities.Wedding;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class GuestsController implements Initializable {
    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtCPF;

    @FXML
    private JFXComboBox<String> cmbSex;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> observableList = FXCollections.observableArrayList(Arrays.asList("Masculino",
                "Feminino"));
        cmbSex.setItems(observableList);
    }

    public void closeApp(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void addGuest(ActionEvent actionEvent) {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String cpf = txtCPF.getText();
        String sex = cmbSex.getSelectionModel().getSelectedItem();

        Person person = new Person(name, cpf, email, sex);

        PersonDaoJDBC personDaoJDBC = new PersonDaoJDBC(DB.getConnection());
        personDaoJDBC.insert(person);

        WeddingDaoJDBC weddingDaoJDBC = new WeddingDaoJDBC(DB.getConnection());
        int idMarriage = weddingDaoJDBC.idMarriage(Program.currentUser.getId());

        ConvidaDaoJDBC convidaDaoJDBC = new ConvidaDaoJDBC(DB.getConnection());
        //Program.currentUser.
    }
}
