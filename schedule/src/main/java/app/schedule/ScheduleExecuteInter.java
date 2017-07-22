package app.schedule;

import app.schedule.entity.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
public interface ScheduleExecuteInter {
    public void upService(Service service);
    public void downServcie(Service service);
}
