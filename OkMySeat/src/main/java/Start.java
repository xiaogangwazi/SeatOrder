import bean.*;
import ch.qos.logback.core.util.ExecutorServiceUtil;
import com.alibaba.fastjson.JSON;
import common.Message;
import commonUtils.Response;
import core.Order.OrderAction;
import core.category.CategoryService;
import core.login.LoginAction;
import org.omg.PortableServer.ThreadPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public  class Start {
    private static  final Logger logger = LoggerFactory.getLogger(Start.class);
    private static LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();
    private static  ArrayList<Runnable> cache= new ArrayList<>();
    private static  List<Future> list = new ArrayList<>();
    private static  ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100,110,98,TimeUnit.MICROSECONDS,linkedBlockingQueue,new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws ParseException {

       /* getUserInfo("201626903015","123456");
        */
      /* getUserInfo("201626702031","666666");*/

        Timer timer = new Timer();
        Calendar calendar= Calendar.getInstance();


        calendar.set(Calendar.HOUR_OF_DAY,21);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,0);
        Calendar calendar2= Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY,21);
        calendar2.set(Calendar.MINUTE,59);
        calendar2.set(Calendar.SECOND,40);

       timer.schedule(new TimerTask() {
            @Override
            public void run() {
                simpleAddTask();
                addTask();
            }
        },calendar.getTime(),24*1000*60*60);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                cycleSubmit();
            }
        },calendar2.getTime(),500);

       /* SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = dateFormat.parse("2019-07-24 13:00:00");
        getCategoryInfo(new SearchForm(Long.toString(parse.getTime()/1000),"10800","1",Message.spaceCategory.category_id,Message.spaceCategory.secondNorth_content_id),"api_access_token=UtQ2S19HUBZNwttSJYfJkIEsQqkf0ms9Q6Al3pAU0fkkjA106SQnYkkN6MwATYH_5cfcb9a6465576d9c8a968e9; expires=Mon, 29-Jul-2019 09:21:58 GMT; Max-Age=2592000; path=/;is_remember=a5dcSqYukJoKWj1IbcEGtamk8pts18ryc1NiLkX0kA; expires=Mon, 29-Jul-2019 09:21:58 GMT; Max-Age=2592000; path=/; HttpOnly;auth=7149A0VCfhqsY4xumgO6ysz0fb0tPCFudiHXubWB1Rra_lZxb2ZyChcKaEX9giHmSTY8mbouLDnwDNsL4g; expires=Mon, 29-Jul-2019 09:21:58 GMT; Max-Age=2592000; path=/; HttpOnly;login_time=1561800118.103; expires=Mon, 29-Jul-2019 09:21:58 GMT; Max-Age=2592000; path=/;uid=ded47zhPGaAPNGW8gGgE-q3vxUBod_JMaK3lYT6u4c8tIV4; expires=Mon, 29-Jul-2019 09:21:58 GMT; Max-Age=2592000; path=/;org_id=142; expires=Mon, 29-Jul-2019 09:21:57 GMT; Max-Age=2592000; path=/;");
    */
    }

    /**
     * 指定位置抢座，免登陆
     */
    public static  void simpleAddTask(){
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(new File("src\\seats.txt")));
            char[] chars = new char[100];
            int count=-1;
            StringBuffer s = new StringBuffer();
            while((count=inputStreamReader.read(chars))!=-1){
                s.append(chars,0,count);
            }
            List<OrderForm> orderForms = JSON.parseArray(s.toString(), OrderForm.class);

            orderForms.stream().forEach(res -> {
                OrderForm orderForm = (OrderForm) res;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date parse = null;
                try {

                    parse = dateFormat.parse(orderForm.getBeginTime());
                    orderForm.setBeginTime(Long.toString(parse.getTime() / 1000L));
                    String duration = orderForm.getDuration();
                    orderForm.setDuration(Integer.valueOf(duration) * 3600 + "");
                    orderForm.setSeatBookers(orderForm.getSeatBookers());
                    Task task = new Task();
                    task.setOrderForm(orderForm);
                    task.setCookie(orderForm.getCookie());
                    cache.add(task);
                } catch (ParseException e) {
                    logger.warn("用户【" + orderForm.getName() + "】的日期格式错误！（yyyy-MM-dd HH:mm:ss）");
                } catch (Exception e){
                    logger.error(e.getMessage());
                }

            });



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取指定自习室的位置信息，生成txt文件存储
     * @param searchForm
     * @param cookie
     */
    public  static  Map<String,Object> getCategoryInfo(SearchForm  searchForm,String cookie){
        Map<String, Object> categoryInfo = CategoryService.getCategoryInfo(searchForm, cookie);
        List<Seat> list = (List<Seat>)categoryInfo.get(Message.spaceCategory.LIST);

        File file = new File("src\\CategoryInfo.txt");
        String s1 = JSON.toJSONString(list);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(s1.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return categoryInfo;
    }

    /**
     * 根据推荐位置选座，免登陆
     */
    public static   void addTask(){
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(new File("src\\seats.txt")));
            char[] chars = new char[100];
            int count=-1;
            StringBuffer s = new StringBuffer();
            while((count=inputStreamReader.read(chars))!=-1){
                s.append(chars,0,count);
            }
            List<OrderForm> orderForms = JSON.parseArray(s.toString(), OrderForm.class);

                orderForms.stream().forEach(res -> {
                    OrderForm orderForm = (OrderForm) res;

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date parse = null;
                    try {

                        parse = dateFormat.parse(orderForm.getBeginTime());
                        orderForm.setBeginTime(Long.toString(parse.getTime() / 1000L));
                        String duration = orderForm.getDuration();
                        orderForm.setDuration(Integer.valueOf(duration) * 3600 + "");
                        Map<String, Object> cookie = CategoryService.getCategoryInfo(new SearchForm(orderForm.getBeginTime(), orderForm.getDuration(), 1 + "", Message.spaceCategory.category_id, Message.spaceCategory.secondNorth_content_id),orderForm.getCookie());
                        Seat o=null;
                        if(((Message.Statue)cookie.get("statue")).getStaute()!=404) {
                             o = (Seat) cookie.get(Message.spaceCategory.bestPairSeats);
                             if(cookie.get("adjustTime")!=null&&cookie.get("adjustDate")!=null) {
                                 Integer adjustTime = (Integer) cookie.get("adjustTime");
                                 Integer adjustDate = (Integer) cookie.get("adjustDate");
                                 orderForm.setDuration(Integer.toString(adjustTime));
                                 orderForm.setBeginTime(Integer.toString(adjustDate / 1000));
                             }
                        }
                        if (o == null) {
                            o = new Seat();
                            o.setId("26765");

                        }

                        orderForm.setSeats(o.getId());


                        orderForm.setSeatBookers(orderForm.getSeatBookers());
                        Task task = new Task();
                        task.setOrderForm(orderForm);
                       /* task.setCookie((String) login.get("cookie"));*/
                        task.setCookie(orderForm.getCookie());
                        cache.add(task);
                    } catch (ParseException e) {
                        logger.warn("用户【" + orderForm.getName() + "】的日期格式错误！（yyyy-MM-dd HH:mm:ss）");
                    } catch (Exception e){
                        logger.error(e.getMessage());
                    }

                });



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *获取用户信息，并添加到用户文件中
     * @param name
     * @return
     */
    public static void getUserInfo(String name,String password){
        Map<String, Object> login = LoginAction.login(name,password);
        User user = (User)login.get("user");
        String cookie = (String) login.get("cookie");
        user.setCookie(cookie);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src\\user.txt",true);
           fileOutputStream.write(user.toString().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("用户查询失败");
        }
    }

    /**
     * 轮询缓存任务列表，添加任务
     */
    public static  void cycleSubmit(){
        int count=0;
        while(count<1000){
            for (Runnable runnable:cache){
                threadPoolExecutor.execute(runnable);
            }
            count++;
        }

    }


}
