package core.Order;

import bean.Order;
import bean.OrderForm;
import bean.SearchForm;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import common.Message;
import commonUtils.Request;
import commonUtils.Response;
import core.RequestHandle;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DateFormatUtil;

import java.util.*;


public class OrderAction {
       private static final Logger logger = LoggerFactory.getLogger(OrderAction.class);

    /**
     * 预定座位
     * @param orderForm
     * @param cookie
     * @return
     */
    public static Response orderSeat(OrderForm orderForm, String cookie){
        System.out.println("开始预订座位【"+orderForm.getSeats()+"】"+":"+orderForm.toString());
        Request request = new Request(Message.URL.BOOKSEAT, Request.Method.POST);
        Map<String,Object> map = new HashMap<>();
        request.setConnectTimeOut("6000");
        request.setReadTimeOut("0");
      request.addHead("Cookie",cookie);
      request.addHead("Host",Message.Login.Host);
        request.setData(orderForm.toString());
        RequestHandle requestHandle = new RequestHandle(request);
        Response execute = requestHandle.execute();
        JSONObject jsonObject = JSON.parseObject(execute.getData());
        JSONObject data = jsonObject.getJSONObject("DATA");
        logger.info("【用戶："+data.getString("uname")+",登陆："+data.getBooleanValue("is_login")+"】==>"+data.getString("msg"));
        return execute;

    }

    /**
     * 获得预定座位信息
     * @param cookie
     * @return
     */
    public static  List<Order> orderList(String cookie){
        Request request = new Request(Message.URL.ORDERLIST, Request.Method.GET);
        request.addHead("Cookie",cookie);
        request.addHead("Content-Type","application/json; charset=utf-8");
        RequestHandle requestHandle = new RequestHandle(request);
        Response execute = requestHandle.execute();
        String data = execute.getData();
        JSONObject jsonObject = JSON.parseObject(data);
        JSONObject content = jsonObject.getJSONObject("content");
        JSONArray defaultItems = content.getJSONArray("defaultItems");

        List<Order> list = new ArrayList<>();
        defaultItems.forEach((res)->{
            Order order = new Order();
            JSONObject j=(JSONObject)res;
             order.setRoomName((String)j.get("roomName"));
             String duration=(String) j.get("duration");
             order.setDuration(DateFormatUtil.getHourAndMunite(Long.valueOf(duration)));
             order.setSeatNum((String)j.get("seatNum"));
             order.setStatus((String)j.get("status"));
            String time = (String) j.get("time");
            order.setTime(DateFormatUtil.getTime((Long.valueOf(time))));
            String orderTime= (String)j.get("orderTime");
            order.setOrderTime(DateFormatUtil.getTime(Long.valueOf(orderTime)));
             list.add(order);
        });
        return list;
    }

}
