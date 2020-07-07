package com.qjl.iot.view;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PdfView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String templateName= (String) model.get("templateName");
        String fileName= (String) model.get("fileName");
        List<Map<String,Object>> data = (List<Map<String,Object>>) model.get("data");

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+".pdf\"");

        document.add(new Paragraph("Generated Users " + LocalDate.now()));

        Map<String,Object> item1=data.get(0);
        PdfPTable table = new PdfPTable(item1.size());
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        cell.setPadding(5);

        Iterator iterator=item1.keySet().iterator();
        String[] headArry=new String[item1.keySet().size()];
        int hc=0;
        while (iterator.hasNext()){
            cell.setPhrase(new Phrase(String.valueOf(iterator.next()), font));
            table.addCell(cell);
            headArry[hc]=String.valueOf(iterator.next());
            hc++;
        }

        for(Map<String,Object> item : data){
            for(int j=0;j<headArry.length;j++){
                table.addCell(String.valueOf(item.get(headArry[j])));
            }
        }

        document.add(table);
    }
}
