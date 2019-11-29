package br.ufop.brTomaz.controllers;

import br.ufop.brTomaz.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ServicesController implements Initializable {
    @FXML
    private Pane contentArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void closeApp(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void openAddService(ActionEvent actionEvent) throws IOException {
        Utils.setView("/views/AddService.fxml", contentArea);
    }
}
