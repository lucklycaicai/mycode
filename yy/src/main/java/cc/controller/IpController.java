package cc.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.mapper.LogMapper;

import com.alibaba.fastjson.JSONObject;

@Controller
public class IpController {
	String address = "null";
	
	@Autowired
	LogMapper logMapper;
	
	 public void printLog(String data,String path)
	    {
		 
		 FileWriter fw = null;
		 try {
		 //如果文件存在，则追加内容；如果文件不存在，则创建文件
		 File f=new File(path+"log.txt");
		 
		 fw = new FileWriter(f, true);
		 address = f.getCanonicalPath();
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		 PrintWriter pw = new PrintWriter(fw);
		 pw.println(data);
		 pw.flush();
		 try {
		 fw.flush();
		 pw.close();
		 fw.close();
		 } catch (IOException e) {
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
	      String str = "ip1:"+ip1+"   ip2:"+ip2+"  ip3:"+ip3+"   ip:"+ip+"   ";
		  get(ip,str);
		  System.out.println("=========end========");
		  return "index.html?ip="+address;
	}
	public void get(String strIP,String str) {
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
			String text = data.get("area")+" "+data.get("country")+" "+data.get("city")+" "+data.get("county")+" "+data.get("region")+" "+data.get("isp");
		
			Map param = new HashMap();
			param.put("time", new Date());
			param.put("text", str+text);
		}   
		catch( IOException e)   { 
			e.printStackTrace();
		} 
		
	} 

}
