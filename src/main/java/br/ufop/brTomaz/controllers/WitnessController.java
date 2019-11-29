package br.ufop.brTomaz.controllers;

import br.ufop.brTomaz.model.entities.Person;
import br.ufop.brTomaz.util.Constraints;
import br.ufop.brTomaz.util.Singleton;
import br.ufop.brTomaz.util.Utils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class WitnessController implements Initializable {
    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtCPF;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXComboBox<String> cmbSex;

    @FXML
    private JFXButton btnNext;

    @FXML
    private Pane contentArea;

    @FXML
    private Label labelWitness;

    private Boolean isFirstWitness = false;

    public void closeApp(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void backPage(MouseEvent mouseEvent) throws IOException {
        Utils.setView("/views/RegisterView.fxml", contentArea);
    }

    public void nextPage(ActionEvent actionEvent) throws IOException {
        String name = txtName.getText();
        String cpf = txtCPF.getText();
        String email = txtEmail.getText();
        String sex = cmbSex.getSelectionModel().getSelectedItem();

        Person witness = new Person(name, cpf, email, sex);

        if(isFirstWitness) {
            Singleton.getInstance().setWitness1(witness);

            if (!btnNext.isDisable()) {
                Utils.setView("/views/WitnessView.fxml", contentArea);
            }
        }
        else {
            Singleton.getInstance().setWitness2(witness);
            if (!btnNext.isDisable()) {
                Utils.setView("/views/CivilView.fxml", contentArea);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Singleton.getInstance().getWitness1() == null) {
            labelWitness.setText("Primeira testemunha");
            isFirstWitness = true;
        }
        else {
            labelWitness.setText("Segunda Testemunha");
        }

        ObservableList<String> observableList = FXCollections.observableArrayList(Arrays.asList("Masculino",
                "Feminino"));
        cmbSex.setItems(observableList);

        SimpleBooleanProperty sizeCPF = new SimpleBooleanProperty(true);

        txtCPF.textProperty().addListener((ob, ov, nv) -> {
            sizeCPF.setValue(nv.length() < 11);
        });

        Constraints.setTextFieldInteger(txtCPF);
        Constraints.setTextFieldMaxLength(txtCPF, 11);

        btnNext.disableProperty().bind(
                txtName.textProperty().isEmpty()
                        .or(txtCPF.textProperty().isEmpty()
                                .or(txtEmail.textProperty().isEmpty()
                                        .or(sizeCPF)
                               )
                        )
        );
    }
}
