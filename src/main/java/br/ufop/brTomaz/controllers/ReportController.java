package br.ufop.brTomaz.controllers;

import br.ufop.brTomaz.util.Utils;
import com.itextpdf.text.Image;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void closeApp(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void generateReport(ActionEvent actionEvent) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Relatório.pdf"));
            document.open();
            Utils.generateReport(document);
            //Desktop.getDesktop().open(new File("/home/brunohenrique/Google Drive/UFOP/Sexto Período/Banco de Dados/Trabalho/celebrate/Relatório.pdf"));
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
