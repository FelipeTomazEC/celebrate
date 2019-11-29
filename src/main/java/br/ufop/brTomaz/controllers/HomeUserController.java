package br.ufop.brTomaz.controllers;

import br.ufop.brTomaz.application.Program;
import br.ufop.brTomaz.model.dao.impl.MarriageDaoJDBC;
import br.ufop.brTomaz.model.dao.impl.WeddingDaoJDBC;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.entities.Marriage;
import br.ufop.brTomaz.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeUserController implements Initializable {
    @FXML
    private Label place;

    @FXML
    private Label data;

    @FXML
    private Pane contentArea;

    @FXML
    private PieChart pieServices;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        WeddingDaoJDBC weddingDaoJDBC = new WeddingDaoJDBC(DB.getConnection());
        int idMarriage = weddingDaoJDBC.idMarriage(Program.currentUser.getId());

        MarriageDaoJDBC marriageDaoJDBC = new MarriageDaoJDBC(DB.getConnection());
        Marriage marriage = marriageDaoJDBC.findById(idMarriage);

        place.setText(marriage.getPlace());
        String[] dat = marriage.getDate().toString().split("-");
        data.setText(dat[2] + "/" + dat[1] + "/" + dat[0]);

        loadServices(pieServices);
    }

    private void loadServices(PieChart pieChart) {
        ObservableList<PieChart.Data> data =
                FXCollections.observableArrayList(
                        new PieChart.Data("Serviço 1", 20),
                        new PieChart.Data("Serviço 2", 20)
                );
        pieChart.setData(data);
    }

    public void closeApp(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void backLogin(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
        Program.stage.getScene().setRoot(parent);
    }

    public void openServices(ActionEvent actionEvent) throws IOException {
        Utils.setView("/views/ServicesView.fxml", contentArea);
    }

    public void backHome(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/views/HomeUserView.fxml"));
        Program.stage.getScene().setRoot(parent);
    }

    public void openProfile(ActionEvent actionEvent) throws IOException {
        Utils.setView("/views/ProfileView.fxml", contentArea);
    }

    public void openGuests(ActionEvent actionEvent) throws IOException {
        Utils.setView("/views/GuestsView.fxml", contentArea);
    }

    public void openReport(ActionEvent actionEvent) throws IOException {
        Utils.setView("/views/ReportView.fxml", contentArea);
    }

    public void openCivilModified(ActionEvent actionEvent) throws IOException {
        Utils.setView("/views/CivilModifiedView.fxml", contentArea);
    }

    public void openMarriageModified(ActionEvent actionEvent) throws IOException {
        Utils.setView("/views/MarriageModifiedView.fxml", contentArea);
    }
}
