package br.ufop.brTomaz.controllers;

import br.ufop.brTomaz.application.Program;
import br.ufop.brTomaz.model.dao.DaoFactory;
import br.ufop.brTomaz.model.dao.PersonDao;
import br.ufop.brTomaz.model.dao.SpouseDao;
import br.ufop.brTomaz.model.dao.impl.PersonDaoJDBC;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.db.DbException;
import br.ufop.brTomaz.model.entities.Spouse;
import br.ufop.brTomaz.security.SegurancaSistema;
import br.ufop.brTomaz.util.Utils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static br.ufop.brTomaz.application.Program.*;

public class LoginController implements Initializable {
    @FXML
    JFXTextField txtEmail;

    @FXML
    JFXPasswordField txtPassword;

    @FXML
    JFXButton btnSignIn;

    @FXML
    private Pane contentArea;

    private BooleanProperty permission = new SimpleBooleanProperty(false);
    private IntegerProperty tries = new SimpleIntegerProperty(3);
    @FXML
    public void openRegister(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Utils.setView("/views/RegisterView.fxml", contentArea);
    }

    @FXML
    public void closeApp(javafx.scene.input.MouseEvent mouseEvent) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        permission.addListener((ob, ov, nv) -> {
            if(nv) {
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("/views/HomeUserView.fxml"));
                    stage.getScene().setRoot(parent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        tries.addListener((ob, ov, nv) -> {
            if(nv.intValue() > 0 && !permission.getValue()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Senha inválida");
                alert.setContentText("A senha digitada é inválida! Tentativas restantes: " + nv.intValue());
                alert.show();;
                txtPassword.clear();
            }
            else if(!permission.getValue()) {
                System.exit(0);
            }

            btnSignIn.disableProperty()
                    .bind(txtEmail.textProperty().isEmpty()
                    .or(txtPassword.textProperty().isEmpty()));
        });
    }

    public void signIn(ActionEvent actionEvent) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        PersonDaoJDBC person = new PersonDaoJDBC(DB.getConnection());
        currentUser = person.retriveUser(email);

        if(currentUser != null)
        {
            SpouseDao spouseDao = DaoFactory.createSpouse();
            Spouse spouse = spouseDao.findById(currentUser.getId());
            permission.setValue(spouse.getPassword().equals(SegurancaSistema.criptografarSenha(password)));
            tries.set(tries.getValue() - 1);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Usuário inválido");
            alert.setContentText("O email digitado não está cadastrado no sistema.");
            alert.show();

            txtEmail.clear();
            txtPassword.clear();
        }
    }
}
