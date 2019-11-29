package br.ufop.brTomaz.controllers;

import br.ufop.brTomaz.application.Program;
import br.ufop.brTomaz.model.entities.Spouse;
import br.ufop.brTomaz.util.Utils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Register1Controller implements Initializable {
    protected static Spouse spouse1;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtCPF;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXComboBox<String> cmbSex;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtConfirmPassword;

    @FXML
    private JFXButton btnNext;

    @FXML
    private Pane contentArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> observableList = FXCollections.observableArrayList(Arrays.asList("Masculino",
                "Feminino"));
        cmbSex.setItems(observableList);

        SimpleBooleanProperty sizeCPF = new SimpleBooleanProperty(true);
        SimpleBooleanProperty sizePhone = new SimpleBooleanProperty(true);

        txtCPF.textProperty().addListener((ob, ov, nv) -> {
            sizeCPF.setValue(nv.length() < 11);
        });

        txtPhone.textProperty().addListener((ob, ov, nv) -> {
            sizePhone.setValue(nv.length() < 11);
        });

        btnNext.disableProperty().bind(
                txtName.textProperty().isEmpty()
                        .or(txtCPF.textProperty().isEmpty()
                                .or(txtEmail.textProperty().isEmpty()
                                        .or(txtPassword.textProperty().isEmpty()
                                                .or(txtPhone.textProperty().isEmpty()
                                                        .or(txtConfirmPassword.textProperty().isEmpty())
                                                        .or(sizeCPF)
                                                        .or(sizePhone)
                                                )
                                        )
                                )
                        )
        );
    }

    public void backPage(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
        Program.stage.getScene().setRoot(parent);
    }

    public void closeApp(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void nextPage() throws IOException {
        String name = txtName.getText();
        String cpf = txtCPF.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String sex = cmbSex.getSelectionModel().getSelectedItem();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        spouse1 = new Spouse(name, cpf, email, password, phone);

        if(!btnNext.isDisable()) {
            Utils.setView("/views/RegisterView2.fxml", contentArea);
        }
    }
}
