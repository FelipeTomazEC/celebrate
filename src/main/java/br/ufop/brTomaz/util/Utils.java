package br.ufop.brTomaz.util;

import com.itextpdf.text.Image;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.awt.Font;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static void setView(String path, Pane contentArea) throws IOException {
        Parent parent = FXMLLoader.load(Utils.class.getResource(path));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(parent);
    }

    public static void generateReport(Document document) throws IOException, DocumentException {
        Image image = Image.getInstance("/home/brunohenrique/Google Drive/UFOP/Sexto Período/Banco de Dados/Trabalho/celebrate/src/main/resources/views/images/logo-branca.png");
        image.setAlignment(Element.ALIGN_CENTER);
        image.scalePercent(50.0f);
        document.add(image);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        Paragraph date = new Paragraph("Emitido em: " + sdf.format(new Date()));
        date.setAlignment(Element.ANNOTATION);
        document.add(date);

        document.add(new Paragraph("\n\n"));

        Paragraph brideAndGroomNames = new Paragraph("Nome-noivo & Nome-noiva",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD,
                        18,
                        Font.BOLD,
                        BaseColor.BLACK
                ));
        document.add(brideAndGroomNames);
        document.add(new Paragraph("\n\n"));

        PdfPTable pTableServices = new PdfPTable(5);

        PdfPCell pCell = new PdfPCell(new Paragraph("Serviços",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD,
                        15)));

        pCell.setColspan(5);
        pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pCell.setBackgroundColor(new BaseColor(217, 177, 217));
        pTableServices.addCell(pCell);

        // Columns table
        pTableServices.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableServices.addCell(new Paragraph("Valor", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableServices.addCell(new Paragraph("CNPJ (Empresa)", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableServices.addCell(new Paragraph("Nome (Empresa)", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableServices.addCell(new Paragraph("Telefone (Empresa)", FontFactory.getFont(FontFactory.HELVETICA,
                12)));

        // Values
        pTableServices.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableServices.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableServices.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableServices.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableServices.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));

        PdfPTable pTableWitness = new PdfPTable(3);

        pCell = new PdfPCell(new Paragraph("Testemunhas",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD,
                        15)));

        pCell.setColspan(3);
        pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pCell.setBackgroundColor(new BaseColor(217, 177, 217));
        pTableWitness.addCell(pCell);

        // Columns table
        pTableWitness.addCell(new Paragraph("CPF", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableWitness.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableWitness.addCell(new Paragraph("Email", FontFactory.getFont(FontFactory.HELVETICA,
                12)));


        // Values
        pTableWitness.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableWitness.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableWitness.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));

        pTableWitness.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableWitness.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableWitness.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));

        PdfPTable pTableGuests = new PdfPTable(3);

        pCell = new PdfPCell(new Paragraph("Convidados",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD,
                        15)));

        pCell.setColspan(3);
        pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pCell.setBackgroundColor(new BaseColor(217, 177, 217));
        pTableGuests.addCell(pCell);

        // Columns table
        pTableGuests.addCell(new Paragraph("CPF", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableGuests.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableGuests.addCell(new Paragraph("Email", FontFactory.getFont(FontFactory.HELVETICA,
                12)));

        // Values
        pTableGuests.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableGuests.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableGuests.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));

        pTableGuests.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableGuests.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableGuests.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));

        pTableGuests.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableGuests.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));
        pTableGuests.addCell(new Paragraph("Nome", FontFactory.getFont(FontFactory.HELVETICA,
                12)));

        document.add(pTableServices);
        document.add(new Paragraph("\n\n"));

        document.add(pTableWitness);
        document.add(new Paragraph("\n\n"));

        document.add(pTableGuests);
    }
}
