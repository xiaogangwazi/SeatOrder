package commonUtils;

import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义请求
 *
 */
public class Request {
    private Method method;
    private Map<String,String> head;
    private String url;
    private String data;
    private String encode;
    private String readTimeOut;
    private String connectTimeOut;

    public Request(String url,Method method){
        this.url=url;
        this.encode="UTF-8";
        this.method=method;
        head=new HashMap<>();
    }
    public void addHead(String key,String value){
        head.put(key,value);
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Map<String, String> getHead() {
        return head;
    }

    public void setHead(Map<String, String> head) {
        this.head = head;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String  getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public String getReadTimeOut() {
        return readTimeOut;
    }

    public void setReadTimeOut(String readTimeOut) {
        this.readTimeOut = readTimeOut;
    }

    public String getConnectTimeOut() {
        return connectTimeOut;
    }

    public void setConnectTimeOut(String connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    public enum  Method{
        POST,GET,PUT,DELETE;
        private Method(){};
    }

}
