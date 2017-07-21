package app.schedule;

import app.database.dao.ServiceRepository;
import app.database.domain.Monitor_services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
public class ScheduleCenter {
    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    ScheduleAlgorithmInter scheduleAlgorithmInter;

    @Autowired
    ScheduleExecuteInter scheduleExecuteInter;

    @Scheduled(fixedRate = 30000l)
    public void doSchedule(){
        List<Monitor_services> services = serviceRepository.
    }
}
