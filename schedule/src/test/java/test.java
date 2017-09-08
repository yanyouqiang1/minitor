import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/28.
 */
public class test {
    public static void main(String[] args) {
        String peak="18:30";
        String though = "2:00";

    }
    public static boolean isPeak(String peak,String though,String now){
        String[] peaks = peak.split(":");
        int peakHour = Integer.valueOf(peaks[0]);
        int peakMinute = Integer.valueOf(peaks[1]);
        String[] thoughs = though.split(":");
        int thoughHour = Integer.valueOf(thoughs[0]);
        int thoughMinute = Integer.valueOf(thoughs[1]);

        Calendar calendar = Calendar.getInstance();
        int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
        int nowMinute = calendar.get(Calendar.MINUTE);

        boolean is = false;

        if(nowHour>peakHour)

        return is;
    }

}
