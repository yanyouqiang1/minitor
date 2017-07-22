package app.schedule.rancherImpl;

import app.schedule.ScheduleAlgorithmInter;
import app.schedule.entity.Method;
import app.schedule.entity.Service;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
@Component
public class ScheduleAlgorithm implements ScheduleAlgorithmInter {
    @Override
    public Boolean isServiceOverload(Service service, List<List<Method>> methods) {
        return null;
    }

    @Override
    public Boolean isServiceRelax(Service service, List<List<Method>> methods) {
        return null;
    }

    @Override
    public List<Service> judgeSourceService(List<Service> overloadServices) {
        return null;
    }

    @Override
    public List<Service> adjustRelaxService(List<Service> relaxService) {
        return null;
    }
}
