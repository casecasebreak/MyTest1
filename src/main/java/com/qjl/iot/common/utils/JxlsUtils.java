package com.qjl.iot.common.utils;

import net.sf.jxls.transformer.XLSTransformer;

import java.util.Map;

/**
 * @业务描述：
 * @package_name： com.lenovo.bpms.common.utils
 * @project_name： lenovo-bms
 * @author： ratelfu@qq.com
 * @create_time： 2020-03-23 14:12
 * @copyright (c) ratelfu 版权所有
 */
public class JxlsUtils {


    /**
     *
     * @param templateFilePath  模板文件所在位置完整路径
     * @param model 存放数据
     * @param outputFilePath  最后生成的文件所在路径
     */
    public  static void buildExcel(String templateFilePath, Map<String, Object> model,String outputFilePath) throws Exception{
            XLSTransformer transformer = new XLSTransformer();
            transformer.transformXLS(templateFilePath, model, outputFilePath);
    }

}
