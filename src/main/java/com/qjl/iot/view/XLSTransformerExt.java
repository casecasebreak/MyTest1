package com.qjl.iot.view;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletContext;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class XLSTransformerExt extends XLSTransformer {
	@SuppressWarnings("rawtypes")
	public void transformXLS(ServletContext servletContext, String srcFilePath, Map beanParams, OutputStream os) {
    	try {
//    		ServletContextResource resource = new ServletContextResource(servletContext, srcFilePath);
			InputStream is = new BufferedInputStream(new FileInputStream(srcFilePath));
    		Workbook workbook = transformXLS(is, beanParams);
	        workbook.write(os);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
