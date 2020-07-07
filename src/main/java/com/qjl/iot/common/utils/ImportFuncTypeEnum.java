package com.qjl.iot.common.utils;

/**
 * @业务描述： jxls导入模板功能常量类
 * @package_name： com.lenovo.bpms.common.utils
 * @project_name： lenovo-bms
 * @author： fuyongnan
 */
public enum ImportFuncTypeEnum {

    /**
     *jxls读取excel功能测试
     */
    jxlsReadTestXML("jxlsReadTestXML","jxls读取excel功能测试");


    /**
     * 对应导入模板管理模板管理表中func_type
     */
    private final String type;

    /**
     * 对应导入模板管理模板管理表中func_desc
     */
    private final String desc;

    ImportFuncTypeEnum(String type, String desc) {
        this.type = type;
        this.desc=desc;

    }
    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
