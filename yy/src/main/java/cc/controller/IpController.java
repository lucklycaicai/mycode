package cc.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

@Controller
public class IpController {
	
	
	 public void printLog(String data,String path)
	    {
	     try{

	      File file =new File(path+"log.txt");
	     
	      if(!file.exists()){
	       file.createNewFile();
	      }
	      

	      FileWriter fileWritter = new FileWriter(file.getName(),true);
	             BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	             bufferWritter.write(data);
	             bufferWritter.close();
	            System.out.println( file.getCanonicalPath());
	     }catch(IOException e){
	      e.printStackTrace();
	     }
	    }
	 
	 public static void main(String args[]){
	 }
	
	
	@RequestMapping(value="hello.do")
	public String getIp(HttpServletRequest request){
		  System.out.println("=========into========");
		  System.out.println("path:"+request.getSession().getServletContext().getRealPath("/"));
		  String path = request.getSession().getServletContext().getRealPath("/");
		  
		  String ip = request.getHeader("x-forwarded-for");     
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	         ip = request.getHeader("Proxy-Client-IP"); 
	      }     
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	         ip = request.getHeader("WL-Proxy-Client-IP"); 
	      }     
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	          ip = request.getRemoteAddr();     
	      }     
	     
	     
	      /*System.out.println("ip1:"+request.getHeader("Proxy-Client-IP"));
	      System.out.println("ip2:"+request.getHeader("WL-Proxy-Client-IP")); 
	      System.out.println("ip3:"+request.getRemoteAddr()); 
	      System.out.println("ip:"+ip);*/
	      String ip1 = request.getHeader("Proxy-Client-IP");
	      String ip2 = request.getHeader("WL-Proxy-Client-IP");
	      String ip3 = request.getRemoteAddr();
		  get(ip,path);
		  printLog("\r\n"+"ip1:"+ip1+"   ip2:"+ip2+"  ip3:"+ip3+"   ip:"+ip,path);
		  System.out.println("=========end========");
		  return "index.html";
	}
	public void get(String strIP,String path) {
		try  {     
			 SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			  String time = s.format(new Date());
			strIP = "58.40.17.82";
			URL url = new URL( "http://ip.taobao.com/service/getIpInfo.php?ip=" + strIP);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
			String line = null;
			StringBuffer result = new StringBuffer();
			
			while((line = reader.readLine()) != null)     { 
				result.append(line); 
			}      
			reader.close();
			System.out.println(result);
			JSONObject jsStr = JSONObject.parseObject(result.toString()); 
			JSONObject data = (JSONObject)jsStr.get("data");
//			System.out.println(strIP);
//			System.out.println(data.get("area"));
//			System.out.println(data.get("country"));
//			System.out.println(data.get("city"));
//			System.out.println(data.get("county"));
//			System.out.println(data.get("region"));
//			System.out.println(data.get("isp"));
			printLog("\r\n"+time+"\r\n",path);
			printLog(data.get("area")+" "+data.get("country")+" "+data.get("city")+" "+data.get("county")+" "+data.get("region")+" "+data.get("isp"),path);
		}   
		catch( IOException e)   { 
			e.printStackTrace();
		} 
		
	} 

}
