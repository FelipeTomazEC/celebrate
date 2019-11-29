package br.ufop.brTomaz.controllers;

import br.ufop.brTomaz.util.Utils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
        Utils.setView("/views/RegisterView1.fxml", contentArea);
    }

    @FXML
    public void closeApp(javafx.scene.input.MouseEvent mouseEvent) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
