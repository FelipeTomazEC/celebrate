package br.ufop.brTomaz.controllers;

import br.ufop.brTomaz.application.Program;
import br.ufop.brTomaz.model.dao.impl.PersonDaoJDBC;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.entities.Person;
import br.ufop.brTomaz.util.Constraints;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    JFXButton btnUpdate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnUpdate.disableProperty().bind(
                btnUpdate.textProperty().isEmpty()
                                .or(txtEmail.textProperty().isEmpty()

                                )
        );
    }

    public void closeApp(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void update(ActionEvent actionEvent) {
        String name = txtName.getText();
        String email = txtEmail.getText();
        Person person = new Person(name, Program.currentUser.getCpf(), email, Program.currentUser.getSex());

        PersonDaoJDBC personDaoJDBC = new PersonDaoJDBC(DB.getConnection());

        personDaoJDBC.update(person);
    }
}
