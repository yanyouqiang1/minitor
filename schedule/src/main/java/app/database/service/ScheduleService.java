package app.database.service;

import app.database.dao.ScheduleServiceRepository;
import app.database.domain.Schedule_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22 0022.
 */
@Component
public class ScheduleService {
    @Autowired
    ScheduleServiceRepository scheduleServiceRepository;

    public List<Schedule_service> getScheduleByServiceName(String serviceName){
        return scheduleServiceRepository.findTop30ByName(serviceName);
    }
}
