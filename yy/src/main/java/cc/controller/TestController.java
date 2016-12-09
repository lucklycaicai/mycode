package cc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {


	@RequestMapping(value="good.do")
	public String aaa(){
		return "index.html";
	}
	
	public static void main(String[] args){
	    try {
	        String loginUrl = "http://localhost:8080/yours";
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("name", "zhang"));
	        params.add(new BasicNameValuePair("passwd", "123"));
	             
	        //requestPost(loginUrl,params);
	        requestGet("http://www.baidu.com/");
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e){
	    	e.printStackTrace();
	    }
	}
	
	//1、简单的获取一个HTML页面的内容 
	public static void requestGet(String urlWithParams) throws Exception {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
         
        //HttpGet httpget = new HttpGet("http://www.baidu.com/");
        HttpGet httpget = new HttpGet(urlWithParams);   
        //配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()  
                .setConnectionRequestTimeout(5000)
                .setConnectTimeout(5000)  
                .setSocketTimeout(5000).build();  
        httpget.setConfig(requestConfig); 
         
        CloseableHttpResponse response = httpclient.execute(httpget);        
        System.out.println("StatusCode -> " + response.getStatusLine().getStatusCode());
         
        HttpEntity entity = response.getEntity();        
        String jsonStr = EntityUtils.toString(entity, "utf-8");
        System.out.println(jsonStr);
         
        httpget.releaseConnection();
    }
	
	public static void requestPost(String url,List<NameValuePair> params) throws ClientProtocolException, IOException {
	    CloseableHttpClient httpclient = HttpClientBuilder.create().build();
	         
	    HttpPost httppost = new HttpPost(url);
	        httppost.setEntity(new UrlEncodedFormEntity(params));
	         
	        CloseableHttpResponse response = httpclient.execute(httppost);
	        System.out.println(response.toString());
	         
	        HttpEntity entity = response.getEntity();
	        String jsonStr = EntityUtils.toString(entity, "utf-8");
	        System.out.println(jsonStr);
	         
	        httppost.releaseConnection();
	}
	
	
	
}
