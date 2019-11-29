package br.ufop.brTomaz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    JFXButton btnUpdate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SimpleBooleanProperty sizePhone = new SimpleBooleanProperty(true);

        txtPhone.textProperty().addListener((ob, ov, nv) -> {
            sizePhone.setValue(nv.length() < 11);
        });

        btnUpdate.disableProperty().bind(
                btnUpdate.textProperty().isEmpty()
                                .or(txtEmail.textProperty().isEmpty()
                                                .or(txtPhone.textProperty().isEmpty())
                                                        .or(sizePhone)
                                )
        );
    }

    public void closeApp(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
