package app.schedule;

import app.schedule.entity.Method;
import app.schedule.entity.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
public interface ScheduleServiceInter {
    /***
     *  获取服务的所有方法 监控数据
     * @param serviceName 根据服务名
     * @return
     */
    public List<List<Method>> getServiceMethod(Service service);

    /***
     *  获取所有服务
     * @return
     */
    public List<Service> getAllServcie();
}
