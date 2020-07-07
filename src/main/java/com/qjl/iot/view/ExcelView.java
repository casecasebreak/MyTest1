package com.qjl.iot.view;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        String templateName= (String) model.get("templateName");
        String fileName= (String) model.get("fileName");
        List<Map<String,Object>> data = (List<Map<String,Object>>) model.get("data");

//        if (data.size()>1000){
//            workbook = new SXSSFWorkbook(1000);
//        }

        // change the file name
        response.setHeader("Content-Disposition", "attachment; fileName="+  fileName +";filename*=utf-8''"+ URLEncoder.encode(fileName+".xls","UTF-8"));

        // create excel xls sheet
        Sheet sheet = workbook.createSheet(fileName);
        sheet.setDefaultColumnWidth(30);

        if(data==null){
            return;
        }
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        // create header row
        if(data.size()!=0){
            Map<String,Object> item1=data.get(0);
            Row header = sheet.createRow(0);
            Iterator iterator=item1.keySet().iterator();
            String[] headArry=new String[item1.keySet().size()];
            int hc=0;
            while (iterator.hasNext()){
                Object currObj=iterator.next();
                String currVal=currObj==null?"":String.valueOf(currObj);
                header.createCell(hc).setCellValue(currVal);
                header.getCell(hc).setCellStyle(style);
                headArry[hc]=currVal;
                hc++;
            }

            int rowCount = 1;
            Object currObj=null;
            for(Map<String,Object> item : data){
                Row userRow =  sheet.createRow(rowCount++);
                for(int j=0;j<headArry.length;j++){
                    currObj=item.get(headArry[j]);
                    userRow.createCell(j).setCellValue(currObj==null?"":String.valueOf(currObj));
                }
            }
        }




    }

}
