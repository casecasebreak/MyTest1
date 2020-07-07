package com.qjl.iot.common.utils;

public class CodeUtils {

    /**
     * 随机生成(字符串)
     * length 要生成的字符串的长度
     */
    public static String getRandomString(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        int count = 0;
        while (count <= length - 1) {
            int t = (int) (Math.random() * 123);//抽取的数值小于char类型的“z”（122）
            if ((t >= 48 & t <= 57) | (t >= 65 & t <= 90) | (t >= 97 & t <= 122)) {
                stringBuffer.append((char) t);
                count++;
            }
        }

        return stringBuffer.toString();

    }
}
