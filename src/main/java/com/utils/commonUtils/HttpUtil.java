package com.utils.commonUtils;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;

/**
 * 时间工具类
 */
public class HttpUtil {

    public HttpUtil() {
    }

    /**
     * 使用get方式发起请求
     * @param url 访问路径+?
     * @param queryString key=value&key=value形式字符串
     * @return jsonStr
     */
    public static String doGet(String url, String queryString) {
        String response = null;
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        method.getParams().setParameter("http.protocol.content-charset", "UTF-8");

        try {
            if(StringUtils.isNotBlank(queryString)) {
                method.setQueryString(URIUtil.encodeQuery(queryString));
            }

            client.executeMethod(method);
            if(method.getStatusCode() == 200) {
                response = method.getResponseBodyAsString();
            }
        } catch (URIException var10) {
        } catch (IOException var11) {
        } finally {
            method.releaseConnection();
        }

        return response;
    }

    public static String doPost(String url, Map<String, String> params) {
        StringBuffer result = new StringBuffer();
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        method.getParams().setParameter("http.protocol.content-charset", "UTF-8");
        if(!ObjectUtils.isNull(params)) {
            NameValuePair[] e = new NameValuePair[params.size()];
            int str = 0;

            Map.Entry entry;
            for(Iterator i$ = params.entrySet().iterator(); i$.hasNext(); e[str++] = new NameValuePair((String)entry.getKey(), (String)entry.getValue())) {
                entry = (Map.Entry)i$.next();
            }

            method.setRequestBody(e);
        }

        try {
            client.executeMethod(method);
            if(method.getStatusCode() == 200) {
                BufferedReader var14 = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "UTF-8"));
                String var15 = null;

                while((var15 = var14.readLine()) != null) {
                    result.append(var15);
                }
            }
        } catch (IOException var12) {
        } finally {
            method.releaseConnection();
        }

        return result.toString();
    }
    public static String doPostXml(String urlStr , String xmlStr){
        String result = null;
        try {
            //创建连接
            URL urlClient = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) urlClient.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(xmlStr.getBytes("UTF-8"));
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
            result=sb.toString();
        } catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

}

