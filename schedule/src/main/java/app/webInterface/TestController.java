package app.webInterface;

import app.database.service.StrategyTimePeriodService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/9/14.
 */
@RestController
public class TestController {
    @Autowired
    StrategyTimePeriodService timePeriodService;

    @RequestMapping("/strategy/serviceTimePeriod/update")
    public boolean ServiceTimePeriod(@RequestParam(name = "service") String service,@RequestParam(name = "peak") String peak,@RequestParam(name = "though") String though){
        timePeriodService.insertStrategy(service,peak,though);
        return true;
    }

}
