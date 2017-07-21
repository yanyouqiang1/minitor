package app.schedule;

import app.schedule.entity.Method;
import app.schedule.entity.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
public interface ScheduleAlgorithmInter {

    public List judgeService(Service service, List<Method> methods);
}
