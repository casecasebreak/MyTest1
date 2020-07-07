package com.qjl.iot.common.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @业务描述：
 * @package_name： com.lenovo.bpms.common.utils
 * @project_name： lenovo-bms
 * @author： ratelfu@qq.com
 * @create_time： 2019-12-13 9:08
 * @copyright (c) ratelfu 版权所有
 */
public class FileDownloadUtils {


    /**
     * @param res
     * @param files 一个文件数组
     * @param zipName 压缩包的名字:例如：test.zip/test.rar
     * @param zipBasePath 服务器上保存临时压缩文件的基础路径
     * @return
     * @throws IOException
     */
    public static String downloadFiles(HttpServletResponse res, MultipartFile[] files, String zipName,String zipBasePath) throws IOException {
        try{


            //设置编码字符
            res.setCharacterEncoding("UTF-8");
            //设置下载内容类型
            res.setContentType("application/octet-stream;charset=UTF-8");
            //设置下载的文件名称
            res.setHeader("Content-disposition", "attachment;filename="+"123"+zipName);
            //创建页面返回方式为输出流，会自动弹出下载框
            OutputStream out = res.getOutputStream();


            //将附件中多个文件进行压缩，批量打包下载文件
            String zipFilePath = zipBasePath+File.separator+zipName;

            //压缩文件
            File zip = new File(zipFilePath);
            if (!zip.exists()){
                zip.createNewFile();
            }
            //创建zip文件输出流
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
            zipFile(files,zos);
            zos.close();
            //设置下载的压缩文件名称
            res.setHeader("Content-disposition", "attachment;filename="+zipName);

            //将打包后的文件写到客户端，输出的方法同上，使用缓冲流输出
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFilePath));
            byte[] buff = new byte[bis.available()];
            bis.read(buff);
            bis.close();
            //输出数据文件
            out.write(buff);
            out.flush();//释放缓存
            out.close();//关闭输出流
            //删除临时压缩文件
            //zip.delete();
        }catch(Exception e) {
            e.printStackTrace();
            res.reset();
            res.setCharacterEncoding("UTF-8");
            res.setContentType("text/html;charset=UTF-8");
            res.getWriter().print("<div align=\"center\" style=\"font-size: 30px;font-family: serif;color: red;\">系统内部错误，下载未成功，请联系管理员！</div>"
                    + "<div>错误信息："+e.getMessage()+"</div>");
            res.getWriter().flush();
            res.getWriter().close();
        }
        return null;
    }

    /**
     * 压缩文件
     * @param files
     * @param zos
     * @throws IOException
     * @return
     */
    private static String zipFile(MultipartFile[] files,ZipOutputStream zos) throws IOException {

        //循环读取文件路径集合，获取每一个文件的路径
        for(MultipartFile file : files){
            BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
            //将文件写入zip内，即将文件进行打包
            zos.putNextEntry(new ZipEntry(file.getOriginalFilename()));
            //写入文件的方法
            int size = 0;
            //设置读取数据缓存大小
            byte[] buffer = new byte[1024];
            while ((size = bis.read(buffer)) > 0) {
                zos.write(buffer, 0, size);
            }
            //关闭输入输出流
            zos.closeEntry();
            bis.close();
        }
        return null;
    }
}
