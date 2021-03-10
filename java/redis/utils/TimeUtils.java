package dcits.liufein.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author liufein
 * date 2019/6/1
 */
public class TimeUtils {
    public static ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal.withInitial(SimpleDateFormat::new);

    public static String TimeStamp2Date(long timestamp, String formats) {
        if(formats!=null&&!("".equals(formats.trim()))){
            dateFormat.set(new SimpleDateFormat(formats));
        }
        return dateFormat.get().format(new Date(timestamp*1000L));
    }
    public static long Date2TimeStamp(String date)throws ParseException {
        return Date2TimeStamp(dateFormat.get().parse(date));
    }
    public static long Date2TimeStamp(Date date) {
        long timestamp = date.getTime()/1000L;
        return timestamp;
    }
}
