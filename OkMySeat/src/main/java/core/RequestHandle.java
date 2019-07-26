package core;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import common.Message;
import commonUtils.Request;
import commonUtils.Response;
import exception.URLIsNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 处理请求工具
 */
public class RequestHandle {
        private Logger logger= LoggerFactory.getLogger(RequestHandle.class);
        private static String DEFAULT_READTIMEOUT="6000";
        private static  String DEFAULT_CONNETTIMEOUT="6000";
    private Request request;
   public  RequestHandle(Request request){
       this.request=request;
       validation();
   }
   public Response execute(){
       HttpURLConnection urlConnection =null;
       Response response= new Response();
       try {
           URL url = new URL(request.getUrl());
         urlConnection= (HttpURLConnection) url.openConnection();
         if(request.getConnectTimeOut()==null||request.getConnectTimeOut().equals("")){
             request.setConnectTimeOut(DEFAULT_CONNETTIMEOUT);
         }
         if(request.getReadTimeOut()==null||request.getReadTimeOut().equals("")){
             request.setReadTimeOut(DEFAULT_READTIMEOUT);
         }
         urlConnection.setReadTimeout(Integer.valueOf(request.getReadTimeOut()));
         urlConnection.setConnectTimeout(Integer.valueOf(request.getConnectTimeOut()));
           Map<String, String> head = request.getHead();
           if(head!=null&&!head.isEmpty()){
            head.forEach(urlConnection::addRequestProperty);
           }

           if(request.getMethod().equals(Request.Method.POST)){
               urlConnection.setRequestMethod("POST");
               if(request.getData()!=null){
                   urlConnection.setDoOutput(true);
                   OutputStream outputStream = urlConnection.getOutputStream();
                   outputStream.write(request.getData().getBytes());
                   outputStream.flush();
                   outputStream.close();
               }
           }else{
               urlConnection.setRequestMethod("GET");
           }
           String charSet=request.getEncode();
           if(request.getEncode()==null||request.getEncode().equals("")){
               charSet="utf-8";
           }
           response.setData(parseData(urlConnection.getInputStream(),charSet));//获得返回的数据
           int responseCode = urlConnection.getResponseCode();//获得状态码
           response.setStatusCode(responseCode);
           String responseMessage = urlConnection.getResponseMessage();//获得状态信息
           response.setMessage(responseMessage);
           Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
           List<String> list = headerFields.get("Set-Cookie");
           response.setCookies(parseCookies(list));//获得cookies
           response.setHead(headerFields);//获得响应字段
       } catch (MalformedURLException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }finally {

           urlConnection.disconnect();
          logger.info("请求结束,url:"+request.getUrl());

       }
       return response;
   }

   private void validation(){
       if(request.getUrl().equals("")){
           throw new URLIsNullException("url must be not null！");
       }
   }
   private String parseCookies(List<String> list){
       if(list!=null&&list.size()>0){
           StringBuffer stringBuffer = new StringBuffer();
           list.stream().forEach((res)->{
              stringBuffer.append(res).append(";");
           });
           String s= stringBuffer.toString();
           s.trim().substring(0,s.length()-1);
          return s;
       }else{
           return null;
       }
   }
   private String  parseData(InputStream inputStream ,String charSet){
       StringBuffer stringBuffer = new StringBuffer();
       BufferedReader bufferedReader = null;
       try {
           bufferedReader = new BufferedReader(new InputStreamReader(inputStream,charSet));
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
           logger.error("不支持的编码格式！");
       }
       bufferedReader.lines().forEach(stringBuffer::append);
      return stringBuffer.toString();
   }


}
