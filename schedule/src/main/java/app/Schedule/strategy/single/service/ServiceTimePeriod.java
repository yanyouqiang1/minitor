package app.Schedule.strategy.single.service;

import app.Schedule.AbstractService;
import app.Schedule.ServiceSingleStrategyInter;
import app.Schedule.StrategySingleResult;
import lombok.Data;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/8/30.
 */
@Data
public class ServiceTimePeriod implements ServiceSingleStrategyInter {
    private boolean isPeakHandle = false,isThoughHandle=false;
    @Override
    public StrategySingleResult doStrategy(AbstractService service) {
        switch (getPeriod()) {
            case PERIOD_PEAK:
                if(!isPeakHandle) {
                    isPeakHandle = true;
                    isThoughHandle = false;
                    return StrategySingleResult.UP;
                }
                break;
            case PERIOD_THOUGH:
                if(!isThoughHandle) {
                    isThoughHandle = false;
                    isThoughHandle = true;
                    return StrategySingleResult.DOWN;
                }
                break;
            default:
                System.out.println("输入的时间错误，导致策略失败!");
                break;
        }
        return StrategySingleResult.NORMAL;
    }

    //18:20
    private String peak;
    private String though;

    public ServiceTimePeriod(String peak, String though) {
        this.peak = peak;
        this.though = though;
    }

    private final int PERIOD_PEAK = 1, PERIOD_THOUGH = 0, PERIOD_ERROR = -1;

    private int getPeriod() {
        float peakF = getFloatTime(peak);
        float thoughF = getFloatTime(though);
        float now = getFloatNow();

        if (peakF == thoughF) {
            return PERIOD_ERROR;
        } else if (peakF < thoughF) {
            if (now >= peakF && now < thoughF) {
                return PERIOD_PEAK;
            } else {
                return PERIOD_THOUGH;
            }
        } else {
            if (now >= thoughF && now < peakF) {
                return PERIOD_THOUGH;
            } else {
                return PERIOD_PEAK;
            }
        }
    }

    private float getFloatTime(String strTime) {
        String[] times = strTime.split(":");
        int hour = Integer.valueOf(times[0]);
        int minute = Integer.valueOf(times[1]);
        return hour + minute / 60.0f;
    }

    private float getFloatNow() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return hour + minute / 60.0f;
    }
}
