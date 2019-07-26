package util;

import com.sun.org.apache.xml.internal.security.keys.content.MgmtData;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class DateFormatUtil {
        private static  final int HOUR=3600;
        private static  final int MINUTE=60;
    public static int getHour(Long time){
        int hour=(int)(time/HOUR);

        return hour;
    }
    public static  int getMinutes(Long time){
        int minute=(int)(time/MINUTE);
        return minute;
    }
    public static  String getTime(Long time){
        Date date = new Date(time*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String format = simpleDateFormat.format(date);
        return format;
    }
    public  static String getHourAndMunite(Long time){
        StringBuffer stringBuffer = new StringBuffer();
        int minutes = getMinutes(time);
        int hour=minutes/60;
        int m=minutes%60;
        stringBuffer.append(hour).append(":").append(m);
        return stringBuffer.toString();

    }
}
