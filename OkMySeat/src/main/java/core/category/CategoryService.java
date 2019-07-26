package core.category;

import bean.Category;
import bean.SearchForm;
import bean.Seat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import common.Message;
import commonUtils.Request;
import commonUtils.Response;
import core.RequestHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryService   {
    private  static  final Logger logger= LoggerFactory.getLogger(CategoryService.class);
    /**
     * 获取推荐座位信息和自习室座位信息
     * @param searchForm
     * @param cookie
     * @return
     */
    public static  Map<String,Object> getCategoryInfo(SearchForm searchForm, String cookie) {

        Request request = new Request(Message.URL.bestPairSeatsURL, Request.Method.POST);

        request.setData(searchForm.toString());
        logger.info("开始查找信息【"+searchForm.getContent_id()+"】"+searchForm.toString());
        request.addHead("content-type","application/x-www-form-urlencoded;charset=UTF-8");
        request.addHead("accept","application/json, text/plain, */*");
        request.addHead("Cookie",cookie);
        request.addHead("Content-Length",searchForm.toString().getBytes().length+"");
        request.addHead("User-Agent",Message.Login.UserAgent);
        RequestHandle requestHandle = new RequestHandle(request);
        Response execute = requestHandle.execute();
        String data = execute.getData();
        JSONObject jsonObject = JSON.parseObject(data);
        Map<String, Object> map = new HashMap<>();
        try {
            JSONObject content = jsonObject.getJSONObject("content");
            JSONArray children = content.getJSONArray("children");
            JSONObject jsonObject2 = children.getJSONObject(1);
            Integer adjustDate = (Integer) jsonObject2.get("adjustDate");
            Integer adjustTime = (Integer) jsonObject2.get("adjustTime");
            JSONObject data1 = jsonObject.getJSONObject("data");
            JSONArray poIs = data1.getJSONArray("POIs");
            JSONObject bestPairSeats = data1.getJSONObject("bestPairSeats");
            JSONArray seats = bestPairSeats.getJSONArray("seats");
            JSONObject jsonObject1 = seats.getJSONObject(0);
            Seat seat = JSON.parseObject(jsonObject1.toJSONString(), Seat.class);

           logger.info("推荐座位信息：" + seat.toString());
           if (adjustDate!=null&&adjustTime!=null) {
               logger.info("时间调整为:【" + adjustDate + "," + adjustTime + "】");
           }
            List<Seat> list = new ArrayList<>();
            poIs.forEach(res -> {
                JSONObject j = (JSONObject) res;
                Seat seat1 = JSON.parseObject(j.toJSONString(), Seat.class);
                list.add(seat1);
            });
            map.put(Message.spaceCategory.LIST, list);
            map.put(Message.spaceCategory.bestPairSeats, seat);
            map.put("statue",Message.Statue.SUCCESS);
            map.put("adjustTime",adjustTime);
            map.put("adjustDate",adjustDate);

        }catch (NullPointerException e){
            logger.error(" 没有符合要求的位置信息");
            map.put("statue",Message.Statue.NULL);
        }
        return map;
    }

    /**
     * 获取区域信息，没啥用
     * @param category
     * @param cookie
     * @return
     */
    public static Response getAreaList(Category category,String cookie){
        String url = Message.URL.SEARCHCATEGORYURL+"&space_category%5Bcategory_id%5D="+category.getCategory_id()+"&space_category%5Bcontent_id%5D="+category.getContent_id();

        Request request = new Request(url, Request.Method.GET);
        request.addHead("Cookie",cookie);
        RequestHandle requestHandle = new RequestHandle(request);
        Response execute = requestHandle.execute();
        return execute;

    }


}
