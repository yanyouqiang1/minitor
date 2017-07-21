package app.schedule;

import app.database.dao.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
public class ScheduleCenter {
    @Autowired
    ServiceRepository serviceRepository;

    @Scheduled(fixedRate = 30000l)
    public void doSchedule(){

    }
}
