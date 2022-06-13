package com.example.pointini.services;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.pointini.entities.Operation;
import com.example.pointini.entities.User;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class UserPDFExporter {
    private List<User> listUsers;
    private User user;

    public UserPDFExporter(List<User> listUsers) {
        this.listUsers = listUsers;
    }
    public UserPDFExporter(User user) {
        this.user = user;
    }

    //User
    private void UserInformation(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Nom", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Prenom", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Adresse", font));
        table.addCell(cell);
    }

    private void writeTableDataUserInformation(PdfPTable table) {
            table.addCell(user.getEmail());
            table.addCell(user.getLastName());
            table.addCell(user.getFirstName());
            table.addCell(user.getAdresse());
    }
    //EntrepriseInformation
    private void EntrpriseInformation(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Nom Entreprise", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Matricule Fiscale", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Heure Total", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("CongeParMois", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("PrixHeureSup", font));
        table.addCell(cell);
    }

    private void writeTableDataEntrpriseInformation(PdfPTable table) {
        table.addCell(user.getEntreprise().getLibelle());
        table.addCell(user.getEntreprise().getMatriculeFiscale());
        table.addCell(String.valueOf(user.getEntreprise().getNbheure()));
        table.addCell(String.valueOf(user.getEntreprise().getNbCongePMois()));
        table.addCell(String.valueOf(user.getEntreprise().getPrixHeurSup()));
    }
    //Operation
    private void OperationInformation(PdfPTable table) {
    PdfPCell cell = new PdfPCell();
    cell.setBackgroundColor(Color.BLACK);
    cell.setPadding(5);
    Font font = FontFactory.getFont(FontFactory.HELVETICA);
    font.setColor(Color.WHITE);
    cell.setPhrase(new Phrase("Type Operation", font));
    table.addCell(cell);
    cell.setPhrase(new Phrase("Montant", font));
    table.addCell(cell);
    cell.setPhrase(new Phrase("Nom Operation", font));
    table.addCell(cell);
    cell.setPhrase(new Phrase("Date Creation", font));
    table.addCell(cell);
}

    private void writeTableDataOperationInformation(PdfPTable table) {
        List<Operation> operations=user.getOperation();
        for (Operation operation : operations){
            table.addCell(String.valueOf(operation.getTypeOperation()));
            table.addCell(String.valueOf(operation.getMontant()));
            table.addCell(operation.getLibelle());
            table.addCell(String.valueOf(operation.getDateoperation()));
        }
    }
    //Salaire
    private void SalaireInformation(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.ORANGE);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Tardives", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Heure Total", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Salaire Final", font));
        table.addCell(cell);

    }

    private void writeTableDataSalaireInformation(PdfPTable table) {
        table.addCell(String.valueOf(user.getNbHeureReatardvUser()));
        table.addCell(String.valueOf(user.getNbHeureTravUser()));
        table.addCell(String.valueOf(user.getSalaire()));
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Fiche de paie\n", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph paragraph2 = new Paragraph("\nProcessus de paie Rapport d’audit final Rapport numéro <"+ LocalDateTime.now()+"> de monsieur "+user.getLastName()+" "+user.getFirstName()+" qui travaille à l'entreprise "+user.getEntreprise().getLibelle()+"\n\n", fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph);
        document.add(paragraph2);



        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(12);

        Paragraph p = new Paragraph("Information Utilisateur", font);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        UserInformation(table);
        writeTableDataUserInformation(table);
        document.add(table);
        //=====Entreprise====//
        font.setSize(12);
        Paragraph p1 = new Paragraph("\nEntreprise", font);
        document.add(p1);
        Paragraph pE=new Paragraph("Le profil d'entreprise est le sommaire formel d'une entreprise et de ses activités \n, une sorte de Table d'identité, qui, en plus de fournir les caractéristiques de cette entreprise ,L'heur superieur ,nomber des conges par mois...",fontParagraph);
        document.add(pE);

        PdfPTable table1 = new PdfPTable(5);
        table1.setWidthPercentage(100f);
        table1.setWidths(new float[] {3.5f, 3.5f, 3.5f, 3.5f, 3.5f});
        table1.setSpacingBefore(10);
        EntrpriseInformation(table1);
        writeTableDataEntrpriseInformation(table1);
        document.add(table1);
        //=====Operation====//
        font.setSize(12);

        Paragraph p2 = new Paragraph("\nOperationa", font);
        document.add(p2);
        Paragraph po=new Paragraph("notre employee voici le tableau de votre opération actuelle dans ce mois Prime Penalité \nAvance...\n",fontParagraph);
        document.add(po);
        PdfPTable table2 = new PdfPTable(4);
        table2.setWidthPercentage(100f);
        table2.setWidths(new float[] {3.5f, 3.5f, 3.0f, 3.0f});
        table2.setSpacingBefore(10);
        OperationInformation(table2);
        writeTableDataOperationInformation(table2);
        document.add(table2);

        //=====Salaire====//
        font.setSize(12);

        Paragraph p3 = new Paragraph("\nVotre Salaire de Ce Mois", font);
        document.add(p3);
        Paragraph ps=new Paragraph("Votre salaire ce mois-ci avec le nombre d'heures travaillées et le nombre d'heures tardives.\n",fontParagraph);
        document.add(ps);
        PdfPTable table3 = new PdfPTable(3);
        table3.setWidthPercentage(100f);
        table3.setWidths(new float[] {3.5f, 3.5f, 3.0f});
        table3.setSpacingBefore(10);
        SalaireInformation(table3);
        writeTableDataSalaireInformation(table3);
        document.add(table3);
        Paragraph p9 = new Paragraph("\nSigné a la date "+LocalDateTime.now(), font);
        document.add(p9);



        document.close();

    }
}

