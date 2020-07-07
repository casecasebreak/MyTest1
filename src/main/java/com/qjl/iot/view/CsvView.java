package com.qjl.iot.view;

import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CsvView extends AbstractCsvView {


    @Override
    protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse
            response) throws Exception {

        String templateName= (String) model.get("templateName");
        String fileName= (String) model.get("fileName");
        List<Map<String,Object>> data = (List<Map<String,Object>>) model.get("data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment; fileName="+  fileName +";filename*=utf-8''"+ URLEncoder.encode(fileName+".csv","UTF-8"));

        Map<String,Object> item1=data.get(0);
        String[] header =  new String[item1.keySet().size()];
        Iterator iterator=item1.keySet().iterator();
        int hc=0;
        while (iterator.hasNext()){
            header[hc++]= (String) iterator.next();
        }

        ICsvMapWriter csvWriter = new CsvMapWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        csvWriter.writeHeader(header);

        for (Map<String,Object> item : data) {
            csvWriter.write(item, header);
        }
        csvWriter.close();

    }
}
