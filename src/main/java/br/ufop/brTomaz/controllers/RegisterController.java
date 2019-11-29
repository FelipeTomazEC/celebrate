package br.ufop.brTomaz.controllers;

import br.ufop.brTomaz.application.Program;
import br.ufop.brTomaz.model.entities.Spouse;
import br.ufop.brTomaz.util.Constraints;
import br.ufop.brTomaz.util.Singleton;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
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

   // @FXML
    //private JFXPasswordField txtConfirmPassword;

    @FXML
    private JFXButton btnNext;

    @FXML
    private Pane contentArea;

    @FXML
    private Label labelPerson;

    private Boolean isFirstPerson = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Singleton.getInstance().getSpouse1() == null) {
            labelPerson.setText("Primeira pessoa");
            isFirstPerson = true;
        }
        else {
            labelPerson.setText("Segunda pessoa");
        }

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

        Constraints.setTextFieldInteger(txtCPF);
        Constraints.setTextFieldMaxLength(txtCPF, 11);

        Constraints.setTextFieldInteger(txtPhone);
        Constraints.setTextFieldMaxLength(txtPhone, 11);

        btnNext.disableProperty().bind(
                txtName.textProperty().isEmpty()
                        .or(txtCPF.textProperty().isEmpty()
                                .or(txtEmail.textProperty().isEmpty()
                                        .or(txtPassword.textProperty().isEmpty()
                                                .or(txtPhone.textProperty().isEmpty()
                                                       // .or(txtConfirmPassword.textProperty().isEmpty())
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
        //String confirmPassword = txtConfirmPassword.getText();

        Spouse spouse = new Spouse(name, cpf, email, password, phone, sex);

        if(isFirstPerson) {
            Singleton.getInstance().setSpouse1(spouse);

            if (!btnNext.isDisable()) {
                Utils.setView("/views/RegisterView.fxml", contentArea);
            }
        }
        else {
            Singleton.getInstance().setSpouse2(spouse);

            if(!btnNext.isDisable()) {
                Utils.setView("/views/WitnessView.fxml", contentArea);
            }
        }
    }
}
