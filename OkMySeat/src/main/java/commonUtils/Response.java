package commonUtils;

import java.util.List;
import java.util.Map;

/**
 * 自定义响应
 */
public class Response {

    private int statusCode;
    private String message;
    private String cookies;
    private Map<String , List<String>> head;
    private String data;

    public Map<String, List<String>> getHead() {
        return head;
    }

    public void setHead(Map<String, List<String>> head) {
        this.head = head;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
