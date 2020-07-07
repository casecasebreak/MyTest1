package com.qjl.iot.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DoubleUtils {
    public static String format(Double data) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        if (data != null) {
            BigDecimal money = new BigDecimal(data);
            return df.format(money);
        }else {
            return "";
        }
    }
}
