package br.ufop.brTomaz.controllers;

import br.ufop.brTomaz.model.entities.Person;
import br.ufop.brTomaz.util.Utils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
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
import java.util.ResourceBundle;

public class Witness1Controller implements Initializable {
    protected static Person witness1;

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

    public void closeApp(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void backPage(MouseEvent mouseEvent) throws IOException {
        Utils.setView("/views/RegisterView2.fxml", contentArea);
    }

    public void nextPage(ActionEvent actionEvent) throws IOException {
        String name = txtName.getText();
        String cpf = txtCPF.getText();
        String email = txtEmail.getText();
        String sex = cmbSex.getSelectionModel().getSelectedItem();

        witness1 = new Person(name, cpf, email);

        if(!btnNext.isDisable()) {
            Utils.setView("/views/WitnessView2.fxml", contentArea);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> observableList = FXCollections.observableArrayList(Arrays.asList("Masculino",
                "Feminino"));
        cmbSex.setItems(observableList);

        SimpleBooleanProperty sizeCPF = new SimpleBooleanProperty(true);

        txtCPF.textProperty().addListener((ob, ov, nv) -> {
            sizeCPF.setValue(nv.length() < 11);
        });

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
