package core.login;

import bean.Login;
import bean.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import common.Message;
import commonUtils.Request;
import commonUtils.Response;
import core.RequestHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆
 */
public class LoginAction {
        private static final Logger logger= LoggerFactory.getLogger(LoginAction.class);

    /**
     * 默认密码登陆
     * @param name
     * @return
     */
    public static Map<String,Object> login(String name){
           return login(name, Message.Login.password);
        }

    /**
     * 密码登陆
     * @param name
     * @param password
     * @return
     */
         public static  Map<String,Object> login(String  name,String password){
            Request request = new Request(Message.URL.LOGIN, Request.Method.POST);
             Login login = new Login(name);
             login.setPassword(password);
             request.setData(JSON.toJSONString(login));
             request.addHead("Cookie", Message.Login.cookies);
             request.addHead("User-Agent",Message.Login.UserAgent);
             request.addHead("Host",Message.Login.Host);
             request.setEncode("UTF-8");
            RequestHandle requestHandle = new RequestHandle(request);
             Response execute = requestHandle.execute();
             String data= execute.getData();
             JSONObject jsonObject = JSON.parseObject(data);
             User user = new User();
             user.setId((String )jsonObject.get("id"));
             user.setName((String)jsonObject.get("name"));
             user.setStudent_number((String)jsonObject.get("student_number"));
             Map<String,Object> re= new HashMap<>();
            re.put("user",user);
            re.put("cookie",execute.getCookies());
            re.put("statue",execute.getStatusCode());
            if(execute.getStatusCode()==200){
                logger.info("用户【"+user.getStudent_number()+"】,【id="+user.getId()+"】登陆成功！");
            }
            return re;

    }

}
