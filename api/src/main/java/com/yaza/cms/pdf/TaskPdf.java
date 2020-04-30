/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.pdf;

import com.google.common.io.ByteStreams;
import com.google.zxing.WriterException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.yaza.cms.domain.config.Task;
import com.yaza.cms.domain.util.ColorUtil;
import com.yaza.cms.domain.util.PdfTableUtil;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author tasu
 */
public class TaskPdf {

    public ByteArrayInputStream generatePdf(List<Task> task) throws WriterException, IOException, DocumentException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 20, 20, 20, 20);
//        BaseColor color = ColorUtil.getColor(profile.getTemplateColor());
        BaseColor color = new BaseColor(25, 25, 112);
        PdfWriter.getInstance(document, out);
        Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 17, Font.NORMAL, BaseColor.WHITE);
        Font boldSmall = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
        Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
        Font italic = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC, BaseColor.BLACK);
        Rectangle rect = new Rectangle(5, 5, 590, 837);
        rect.setBorder(Rectangle.BOX);
        rect.setBorderWidth(2);
        rect.setBorderColor(color);
        PdfPTable headerTable = new PdfPTable(1);
        headerTable.setWidthPercentage(100);
        headerTable.setSpacingBefore(5f);
        headerTable.setSpacingAfter(5f);
        PdfPCell headerCell = new PdfPCell(new Paragraph("Consultation Summary", bold));
        headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        headerCell.setPaddingTop(2f);
        headerCell.setPaddingBottom(2f);
        headerCell.setMinimumHeight(36f);
        headerCell.setBackgroundColor(color);
        headerTable.addCell(headerCell);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(5f);
        table.setSpacingAfter(5f);

//        InputStream companyLogo = companyImageResource.getInputStream();
//        byte[] cl = ByteStreams.toByteArray(companyLogo);
//        Image comLogo = Image.getInstance(cl);
//        PdfPCell logoCell = new PdfPCell(comLogo);
//        comLogo.scaleToFit(120f, 100f);
//        comLogo.setAlignment(2);
//        logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        logoCell.setBorder(Rectangle.NO_BORDER);
//        table.addCell(logoCell);

//        String details = profile.getAddress() + "\n\n";
//        details += "OFFICE " + profile.getOfficePhone() + "\n\n";
//        details += "PHONE " + profile.getPhoneNumber() + "\n\n";
//        details += "EMAIL " + profile.getEmail();
//        PdfPCell detailsCell = new PdfPCell(new Paragraph(details, italic));
//        detailsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        detailsCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        detailsCell.setBorder(Rectangle.NO_BORDER);
//        table.addCell(detailsCell);

        PdfPTable contentTable = new PdfPTable(2);
        PdfTableUtil.setUpTable(contentTable, 100, 5f);

        PdfPCell patientCell = new PdfPCell(new Paragraph("Patient Name:", normal));
        PdfTableUtil.setUpCell(patientCell, contentTable, 2f, 36f);

//        PdfPCell patientContent = new PdfPCell(new Paragraph(visit.getPatient().getName(), normal));
//        PdfTableUtil.setUpCell(patientContent, contentTable, 2f, 36f);

        PdfPCell dateCell = new PdfPCell(new Paragraph("Date:", normal));
        PdfTableUtil.setUpCell(dateCell, contentTable, 2f, 36f);
//
//        PdfPCell dateContent = new PdfPCell(new Paragraph(DateUtil.getStringFromDate(visit.getDateCreated()), normal));
//        PdfTableUtil.setUpCell(dateContent, contentTable, 2f, 36f);

        PdfPTable complaintsTable = new PdfPTable(4);
        PdfTableUtil.setUpTable(complaintsTable, 100, 5f);

        PdfPCell complaintLabel = new PdfPCell(new Paragraph("Complaint", normal));
        PdfTableUtil.setUpCell(complaintLabel, complaintsTable, 2f, 36f);
        
        PdfPCell durationLabel = new PdfPCell(new Paragraph("Duration", normal));
        PdfTableUtil.setUpCell(durationLabel, complaintsTable, 2f, 36f);

        PdfPCell periodLabel = new PdfPCell(new Paragraph("Period", normal));
        PdfTableUtil.setUpCell(periodLabel, complaintsTable, 2f, 36f);

        PdfPCell notesLabel = new PdfPCell(new Paragraph("Notes", normal));
        PdfTableUtil.setUpCell(notesLabel, complaintsTable, 2f, 36f);
//        List<PrimaryComplaint> complaints = (hae != null ? hae.getPrimaryComplaints() : null);
//        if(complaints != null){
//        complaints.stream().forEach(item ->{
//            PdfTableUtil.setUpCell(new PdfPCell(new Paragraph(item.getComplaint(), normal)), complaintsTable, 2f, 36f);
//            PdfTableUtil.setUpCell(new PdfPCell(new Paragraph(item.getDuration().toString(), normal)), complaintsTable, 2f, 36f);
//            PdfTableUtil.setUpCell(new PdfPCell(new Paragraph(item.getComplaintPeriod().name(), normal)), complaintsTable, 2f, 36f);
//            PdfTableUtil.setUpCell(new PdfPCell(new Paragraph(item.getNotes(), normal)), complaintsTable, 2f, 36f);
//        });}

        PdfPTable medicineTable = new PdfPTable(5);
        PdfTableUtil.setUpTable(medicineTable, 100, 5f);

        PdfPCell medicineDateLabel = new PdfPCell(new Paragraph("Date", normal));
        PdfTableUtil.setUpCell(medicineDateLabel, medicineTable, 2f, 36f);

        PdfPCell medicineDrugNameLabel = new PdfPCell(new Paragraph("Drug Name", normal));
        PdfTableUtil.setUpCell(medicineDrugNameLabel, medicineTable, 2f, 36f);

        PdfPCell medicationDurationLabel = new PdfPCell(new Paragraph("Duration", normal));
        PdfTableUtil.setUpCell(medicationDurationLabel, medicineTable, 2f, 36f);

        PdfPCell medicationTotalQuantityLabel = new PdfPCell(new Paragraph("Total Quantity", normal));
        PdfTableUtil.setUpCell(medicationTotalQuantityLabel, medicineTable, 2f, 36f);

        PdfPCell medicationFrequencyLabel = new PdfPCell(new Paragraph("Frequency", normal));
        PdfTableUtil.setUpCell(medicationFrequencyLabel, medicineTable, 2f, 36f);

//        medication.stream().forEach(item ->{
//
//            PdfTableUtil.setUpCell(new PdfPCell(new Paragraph(item.getDateCreated().toString(), normal)), medicineTable, 2f, 36f);
//            PdfTableUtil.setUpCell(new PdfPCell(new Paragraph(item.getDrugName().getGeneralName(), normal)), medicineTable, 2f, 36f);
//            PdfTableUtil.setUpCell(new PdfPCell(new Paragraph(item.getDuration().toString()  + " " + item.getDurationUnit(), normal)), medicineTable, 2f, 36f);
//            PdfTableUtil.setUpCell(new PdfPCell(new Paragraph(item.getTotalQuantity().toString() + "" + item.getMedicationUnit(), normal)), medicineTable, 2f, 36f);
//            PdfTableUtil.setUpCell(new PdfPCell(new Paragraph(item.getDose().toString()  + "" + item.getDosageUnit() + " for " + item.getFrequency() + " " + item.getFrequencyUnit() , normal)), medicineTable, 2f, 36f);
//
//        });

        PdfPTable labOrdersTable = new PdfPTable(2);
        PdfTableUtil.setUpTable(labOrdersTable, 100, 5f);

        PdfPCell labOrderCategoryLabel = new PdfPCell(new Paragraph("Lab Order Category", normal));
        PdfTableUtil.setUpCell(labOrderCategoryLabel, labOrdersTable, 2f, 36f);

        PdfPCell labOrderLabel = new PdfPCell(new Paragraph("Lab Order ", normal));
        PdfTableUtil.setUpCell(labOrderLabel, labOrdersTable, 2f, 36f);

//        labOrders.stream().forEach(item ->{
//
//            PdfTableUtil.setUpCell(new PdfPCell(new Paragraph(item.getLabTestName(), normal)), labOrdersTable, 2f, 36f);
//            PdfTableUtil.setUpCell(new PdfPCell(new Paragraph(item.getLabTestOrder().getName() , normal)), labOrdersTable, 2f, 36f);
//        });
//
//        PdfPTable radOrdersTable = new PdfPTable(2);
//        PdfTableUtil.setUpTable(radOrdersTable, 100, 5f);
//
//        PdfPCell radOrderCategoryLabel = new PdfPCell(new Paragraph("Lab Order Category", normal));
//        PdfTableUtil.setUpCell(radOrderCategoryLabel, radOrdersTable, 2f, 36f);
//
//        PdfPCell radOrderLabel = new PdfPCell(new Paragraph("Lab Order ", normal));
//        PdfTableUtil.setUpCell(radOrderLabel, radOrdersTable, 2f, 36f);

//        radiologyOrders.stream().forEach(item ->{
//
//            PdfTableUtil.setUpCell(new PdfPCell(new Paragraph(item.getRadiologyName(), normal)), radOrdersTable, 2f, 36f);
//            PdfTableUtil.setUpCell(new PdfPCell(new Paragraph(item.getRadiologyOrder().getName() , normal)), radOrdersTable, 2f, 36f);
//        });

        PdfPTable nextReviewTable = new PdfPTable(3);
        PdfTableUtil.setUpTable(nextReviewTable, 100, 5f);

        PdfPCell nextDateLabel = new PdfPCell(new Paragraph("Next Review Date", normal));
        PdfTableUtil.setUpCell(nextDateLabel, nextReviewTable, 2f, 36f);

        PdfPCell nextNotesLabel = new PdfPCell(new Paragraph("Notes", normal));
        PdfTableUtil.setUpCell(nextNotesLabel, nextReviewTable, 2f, 36f);

        PdfPCell nextPhysicianLabel = new PdfPCell(new Paragraph("Physician", normal));
        PdfTableUtil.setUpCell(nextPhysicianLabel, nextReviewTable, 2f, 36f);


//        PdfTableUtil.setUpCell(new PdfPCell(new Paragraph((homeReview != null ? homeReview.getNextReviewDate().toString() : "" ), normal)), nextReviewTable, 2f, 36f);
//        PdfTableUtil.setUpCell(new PdfPCell(new Paragraph((homeReview != null ? homeReview.getNotes() : "" ), normal)), nextReviewTable, 2f, 36f);
//        PdfTableUtil.setUpCell(new PdfPCell(new Paragraph((homeReview != null ? homeReview.getPhysician().getDisplayName() : "" ), normal)), nextReviewTable, 2f, 36f);
        document.open();
        document.add(rect);
        document.add(headerTable);
        document.add(table);
        LineSeparator separator = new LineSeparator();
        separator.setLineColor(color);
        document.add(separator);
        document.add(contentTable);
        document.add(separator);
        document.add(new Paragraph("Primary Complaints", boldSmall));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("", boldSmall));
        document.add(new Paragraph("", boldSmall));
        document.add(separator);
        document.add(complaintsTable);
        document.add(separator);
        document.add(new Paragraph("Management Plan", boldSmall));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("", boldSmall));
        document.add(new Paragraph("", boldSmall));
        document.add(separator);
        document.add(separator);
        document.add(new Paragraph("Medication Prescribed", boldSmall));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("", boldSmall));
        document.add(new Paragraph("", boldSmall));
        document.add(separator);
        document.add(medicineTable);
        document.add(separator);
        document.add(new Paragraph("Labs Test Ordered", boldSmall));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("", boldSmall));
        document.add(new Paragraph("", boldSmall));
        document.add(separator);
        document.add(labOrdersTable);
        document.add(separator);
        document.add(new Paragraph("Radiology Test Ordered", boldSmall));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("", boldSmall));
        document.add(separator);
//        document.add(radOrdersTable);
        document.add(new Paragraph("", boldSmall));
        document.add(separator);
        document.add(separator);
        document.add(new Paragraph("Next Visit", boldSmall));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("", boldSmall));
        document.add(separator);
        document.add(nextReviewTable);
        document.add(new Paragraph("", boldSmall));
        document.add(separator);
        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
