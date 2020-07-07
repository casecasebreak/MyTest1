package com.qjl.iot.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class StringUtils {
    public static String formatString(Double data) {
        if (data!=null){
            BigDecimal money = new BigDecimal(data);
            DecimalFormat df = new DecimalFormat("#,##0.00");
            return df.format(money);
        }
        return "";
    }
    public static String newFormatString(Double data) {
        if (data!=null){
            BigDecimal money = new BigDecimal(data);
            DecimalFormat df = new DecimalFormat("#,##0");
            return df.format(money);
        }
        return "";
    }
}
