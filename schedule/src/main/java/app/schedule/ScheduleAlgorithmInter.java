package app.schedule;

import app.schedule.entity.Method;
import app.schedule.entity.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
public interface ScheduleAlgorithmInter {

    /***
     *  判断服务是否负载
     * @param service 服务
     * @param methods 服务下所有方法的监控数据
     * @return 是否负载
     */
    public Boolean isServiceOverload(Service service, List<List<Method>> methods);

    /***
     * 判断服务是否轻松
     * @param service
     * @param methods
     * @return
     */
    public Boolean isServiceRelax(Service service, List<List<Method>> methods);

    /***
     *  判断负载根源 ，确认哪些应该扩展
     * @param overloadServices 负载服务列表
     * @return 应该扩展的服务
     */
    public List<Service> judgeSourceService(List<Service> overloadServices);

    /***
     *  需要缩小容器的服务
     * @param relaxService 负载轻的服务列表
     * @return
     */
    public List<Service> adjustRelaxService(List<Service> relaxService);


}
