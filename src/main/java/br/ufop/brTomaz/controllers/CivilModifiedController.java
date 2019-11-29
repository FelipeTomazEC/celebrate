package br.ufop.brTomaz.controllers;

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CivilModifiedController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void closeApp(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
