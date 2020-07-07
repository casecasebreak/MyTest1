package com.qjl.iot.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 * ZLib压缩工具
 *
 * @author <a href="mailto:zlex.dongliang@gmail.com">梁栋</a>
 * @version 1.0
 * @since 1.0
 */
public abstract class ZLibUtils {

    /**
     * 压缩
     *
     * @param data
     *            待压缩数据
     * @return byte[] 压缩后的数据
     */
    public static byte[] compress(byte[] data) {
        byte[] output = new byte[0];

        Deflater compresser = new Deflater();

        compresser.reset();
        compresser.setInput(data);
        compresser.finish();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
        try {
            byte[] buf = new byte[1024];
            while (!compresser.finished()) {
                int i = compresser.deflate(buf);
                bos.write(buf, 0, i);
            }
            output = bos.toByteArray();
        } catch (Exception e) {
            output = data;
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        compresser.end();
        return output;
    }

    /**
     * 压缩
     *
     * @param data
     *            待压缩数据
     *
     * @param os
     *            输出流
     */
    public static void compress(byte[] data, OutputStream os) {
        DeflaterOutputStream dos = new DeflaterOutputStream(os);

        try {
            dos.write(data, 0, data.length);

            dos.finish();

            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解压缩
     *
     * @param data
     *            待压缩的数据
     * @return byte[] 解压缩后的数据
     */
    public static byte[] decompress(byte[] data) {
        byte[] output = new byte[0];

        Inflater decompresser = new Inflater();
        decompresser.reset();
        decompresser.setInput(data);

        ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
        try {
            byte[] buf = new byte[1024];
            while (!decompresser.finished()) {
                int i = decompresser.inflate(buf);
                o.write(buf, 0, i);
            }
            output = o.toByteArray();
        } catch (Exception e) {
            output = data;
            e.printStackTrace();
        } finally {
            try {
                o.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        decompresser.end();
        return output;
    }

    /**
     * 解压缩
     *
     * @param is
     *            输入流
     * @return byte[] 解压缩后的数据
     */
    public static byte[] decompress(InputStream is) {
        InflaterInputStream iis = new InflaterInputStream(is);
        ByteArrayOutputStream o = new ByteArrayOutputStream(1024);
        try {
            int i = 1024;
            byte[] buf = new byte[i];

            while ((i = iis.read(buf, 0, i)) > 0) {
                o.write(buf, 0, i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return o.toByteArray();
    }

    public static void main(String[] args){
        System.err.println("字节压缩／解压缩测试");
        String inputStr = "zjw";
        System.err.println("输入字符串:\t" + inputStr);
        byte[] input = inputStr.getBytes();
        System.err.println("输入字节长度:\t" + input.length);

        String ss = "ZUp6VmwwMXJFMUVVaHY5TDRPNDZ3N2tmNTM1MDdWSlF4SjI0NktLSVVCUzByc1NWNUtPQkZCY2hDaWxJUWFGQ2FCRkt0QTM5TjVtWjlsOTRTeEpKNXA0NE15SFlCbWFUOTE3dTVIM091ZWVjZWZhK2xuYStadjEyMG1wa1AwZTE3VnJhSFNiMTc3V3Qydlg1UmRZOVNiOGNacU9HMTZQSWEwOWY3Ky9zK1I4U25OSEt4QUJlRk1CZEJOdy9mZ1htTlVGb2NxSVJKeWhpTnhLYUpqUkRhSmJRM0p6MllTc3duM3pyM1RTYmFldFRlalFvUUdEQlNpZGlaMVlsZ0VvSkpXS1FaUUFJb1JYSEdGeWVnalJHWW14eDNTU3l3V0Q4K3lEdFhXVEhnL0ZvV0FDREc2RzRqSTFiRlVaNHdEM0locjhNcnEvT2loa0l0RnBBckZkbUVCNXdueGlrUjUya2ZWeFVGMjZ6MGNacTlUemdVb0NKRWNzdytPK1hZbG9lenZwSjYwZDIyQ3lBWVJRTE1MQUFBc3Nqc0lJRjVsa1FmaFlFbndWdVdlQ1ZCVTRaNmJOUnYvbDRVcW9NU2dNYVpReVl0K3J2czRLRmhYK0VmVUdibWhhZ2dZdzY1MGhsdjFMY0lIRURoTmFMK2hRRzF4cXJCUC9oZzhlbGFxRVZCaDNSR1FPOUlvKzdyQUxlZTZrYXlFRW9SNWtQRnpiSS9aUGRGKy8yZHQ2TUx5K1Rkci9rcmNERjNLMVlCaWtDK1NPbkVMeEtjZUQ1N1liZWJtZTlCNm9RZWZUcTdjdjkzUXBBdUlDMUE1RkFBdUUwa1B6dVdYV2dlZVIzRnpXRzdtbHkwSmtBS0NDeGJncDNPaTcvT2swLzEwdlpOczdndXExejRIUUdJTjBoaE5Sa2grQnVTWWZJZDVvaUhQWGhlTlFyaFFNMVYwaDB6VURmb0d5WXpFVkZoY0J4ZTl2WG5jMWJqeXBOREFKUktsRHozMTlxZHM2U3djRy93S0NtNXNYSStyOEZ4TUFZTFJzZW9xclRROW1oVWZQTkdCcWYvd0YwbURRKw==";

        byte[] d1=Base64.getDecoder().decode(ss);
        byte[] data=Base64.getDecoder().decode(d1);
        // byte[] data = ZLibUtils.compress(input);
        System.err.println("压缩后字节长度:\t" + data.length);
        for(byte bt: data) {
            System.err.print(Integer.toHexString((int)bt & 0xff) + ",");
        }
        for(byte bt: input) {
            System.out.print(bt + ",");
        }
        byte[] output = ZLibUtils.decompress(data);
        System.err.println("解压缩后字节长度:\t" + output.length);
        String outputStr = new String(output);
        System.err.println("输出字符串:\t" + outputStr);
    }
}
