package com.utils.commonUtils;






import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/*
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClientUtil {
    public static String doPost(String url,Map<String,String> map,String charset){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Entry<String,String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    
    /**
     * 利用HttpClient进行post请求的工具类
     * @param url 请求路径以问号结尾
     * @param jsonStr 参数字符长，例如key=value&key2=value
     * @return
     */
    public static String doPost(String url,String jsonStr){
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        String result="error";
        try {
        	StringEntity s = new StringEntity(jsonStr,"UTF-8");
        	s.setContentEncoding("UTF-8");
        	s.setContentType("application/json");//发送json数据需要设置contentType
        	post.setEntity(s);
        	HttpResponse res = client.execute(post);
        	if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
        		result = EntityUtils.toString(res.getEntity());// 返回json格式：
        	}
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
        return result;
    }
        
}