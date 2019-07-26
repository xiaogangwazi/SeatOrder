package common;

import java.util.Map;

public class Message {
    public static class Login{
        public  static  final String code="170d8ce57caa3b7f4b49ac9bc16b8dc5";
        public  static  final String login_name="201626702031";
        public  static  final String org_id="142";
        public  static  final String password="666666";
        public  static  final String str= "HXwkqy2ZgFfAhZRs";
        public  static  final String _ApplicationId="lab4";
        public  static  final String _ClientVersion= "js_xxx";
        public  static  final String _InstallationId="8c9efadf-4b91-424b-ff7f-f445c68ce69c";
        public  static  final String _JavaScriptKey="lab4";
        public static final String cookies="org_id=142; web_language=zh-CN";
        public  static  final  String Host="jxnu.huitu.zhishulib.com";
        public static  final  String content_type="text/plain";
        public static  final  String Accept_Encoding="gzip, deflate, br";
        public  static  final String accept="application/json, text/plain, */*";
        public static  final String  UserAgent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36";
        public static  final  String Referer="https://jxnu.huitu.zhishulib.com/";
        public static  final  String Origin="https://jxnu.huitu.zhishulib.com";
    }
    public class spaceCategory{
        public static  final  String content_type="application/x-www-form-urlencoded;charset=UTF-8";
        public static final String category_id="591";
        public  static  final  String secondNorth_content_id="35";
        public static  final  String secondSouth_content_id="36";
        public static  final  String thirdNorth_content_id="37";
        public static  final  String LAB_JSON="1";
        public static  final  String LIST="list";
        public static final  String bestPairSeats="bestPairSeats";

    }
    public static final class URL{
        public static  final  String bestPairSeatsURL="https://jxnu.huitu.zhishulib.com/Seat/Index/searchSeats?LAB_JSON=1";
        public static  final  String BOOKSEAT="https://jxnu.huitu.zhishulib.com/Seat/Index/bookSeats?LAB_JSON=1";
        public static final String  SEARCHCATEGORYURL="https://jxnu.huitu.zhishulib.com/Seat/Index/searchSeats?LAB_JSON=1";
        public static final String LOGIN="https://jxnu.huitu.zhishulib.com/api/1/login";
        public static  final  String ORDERLIST="https://jxnu.huitu.zhishulib.com/Seat/Index/myBookingList?LAB_JSON=1";
    }
    public static final class Order{
        public static  final  String DEFAULTDURATION="10800";
        public static  final  String DEFAULTNUM="1";

    }
    public static enum Statue{
        SUCCESS(200,"SUCCESS"),NULL(404,"没有推荐位置"),FAIL(500,"FAIL");
        private int statue;
        private String message;
        private Statue(int statue,String message){
            this.message=message;
            this.statue=statue;
        }

        public int getStaute() {
            return statue;
        }

        public void setStaute(int staute) {
            this.statue = staute;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
