import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/28.
 */
public class test {
    public static void main(String[] args) {
        String peak="18:14";
        String though = "2:00";

        System.out.println(getFloatTime(peak));

    }
    private static float getFloatTime(String strTime) {
        String[] times = strTime.split(":");
        int hour = Integer.valueOf(times[0]);
        int minute = Integer.valueOf(times[1]);
        return hour +  minute / 60.0f;
    }
}
