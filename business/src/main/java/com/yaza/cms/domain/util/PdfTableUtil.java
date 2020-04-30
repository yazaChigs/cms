/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.domain.util;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 *
 * @author tasu
 */
public class PdfTableUtil {
    
    public static void setUpCell(PdfPCell cell, PdfPTable table, float padding, float minHeight){
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPaddingTop(padding);
        cell.setPaddingBottom(padding);
        cell.setMinimumHeight(minHeight);
        table.addCell(cell);
    }
    
    public static void setUpTable(PdfPTable table, Integer widthPercentage, float spacing){
        table.setWidthPercentage(widthPercentage);
        table.setSpacingBefore(spacing);
        table.setSpacingAfter(spacing);
    }
}
