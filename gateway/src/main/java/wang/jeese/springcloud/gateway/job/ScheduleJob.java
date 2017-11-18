package wang.jeese.springcloud.gateway.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wang.jeese.springcloud.gateway.service.RefreshRouteService;

/**
 * Created by jeese on 2017/7/24.
 */
@Component
public class ScheduleJob {

    private static Logger logger = LoggerFactory.getLogger(ScheduleJob.class);

    private final static long ONE_Minute =  60 * 1000;

    @Autowired
    private RefreshRouteService refreshAuthorities;

//    @Scheduled(fixedDelay = ONE_Minute)
//    public void refreshRoute(){
//        refreshAuthorities.refreshRoute();
//    }

}
