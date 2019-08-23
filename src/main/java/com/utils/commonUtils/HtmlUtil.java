package com.utils.commonUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


/**
 * @author www.inxedu.com
 */
public class HtmlUtil {

    /**
     * 生成静态页面
     */
    public static String getGenerateIndex(String url){
        String src = getStaticPage(url);
        if(StringUtils.isEmpty(src) || "error".equalsIgnoreCase(src)){
            return "error";
        }

        String staticPath = System.getProperty("webapp.root");
        //生成2个文件。防止删除后，生成过程中访问出错,web.xml里需配置2个文件
        try {
            File file_1 = new File(staticPath+url.substring(url.indexOf(".com")+5,url.length())+".html");
            if(file_1.isFile()){
                file_1.delete();
            }
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file_1, true),"UTF-8");
            osw.write(src);
            osw.close();
        } catch (Exception e) {

        }
        return "success";
    }

    public static void main(String[] args) {
        String url = "http://demo1.inxedu.com/index";
        System.out.println(url.substring(url.indexOf(".com")+5,url.length()));
    }
    private static String getStaticPage(String surl) {
        String htmlContent = "";
        try {
            java.io.InputStream inputStream;
            java.net.URL url = new java.net.URL(surl);
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url
                    .openConnection();
            connection.connect();
            inputStream = connection.getInputStream();
            byte[] bytes = new byte[1024 * 2000];
            int index = 0;
            int count = inputStream.read(bytes, index, 1024 * 2000);
            while (count != -1) {
                index += count;
                count = inputStream.read(bytes, index, 1);
            }
            htmlContent = new String(bytes, "UTF-8");
            connection.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
        return htmlContent.trim();
    }
}
