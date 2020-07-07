package com.qjl.iot.common.utils;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @业务描述：  jxls解析excel工具类
 * @author： fuyongnan
 */
public class JxlsReadUtils {
    private static final Logger log = LoggerFactory.getLogger(JxlsReadUtils.class);

    /**
     * 解析excel到 resultList中
     * @param filePath 导入的excel文件路径
     * @param xmlPath 解析对应excel文件的xml路径
     * @param result  需要解析成的实体类
     * @param resultList 解析结果list
     * @throws Exception
     */
    public static void excelToList(String filePath, String xmlPath, Object result, List resultList) throws Exception {
        InputStream inputXML = new BufferedInputStream(new FileInputStream(new File(xmlPath)));
        XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
        InputStream inputXLS = new BufferedInputStream(new FileInputStream(new File(filePath)));
        Map beans = new HashMap<>();
        beans.put("item", result);
        beans.put("items", resultList);
        XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
        if (readStatus.isStatusOK()) {
            log.info("读取excel表数据成功，共计读取{}条数据", resultList.size());
        } else {
            log.info("读取excel表数据失败");
        }
        inputXML.close();
        inputXLS.close();
    }
}
