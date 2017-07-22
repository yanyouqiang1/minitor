package app.database.service;

import app.database.dao.MethodRepository;
import app.database.dao.ServiceRepository;
import app.database.domain.Monitor_method;
import app.database.domain.Monitor_services;
import app.schedule.entity.Method;
import app.schedule.entity.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
@Component
public class MethodService {
    @Autowired
    MethodRepository methodRepository;

    @Autowired
    ServiceRepository serviceRepository;
    /***
     *  获取服务的所有方法 监控数据
     * @param serviceName
     * @return
     */
    public List<List<Method>> getServiceMethod(String serviceName){
        List<List<Method>> serviceMethods= new LinkedList<List<Method>>();
        //找到服务id
        long serviceid  = serviceRepository.findDistinctIdFirstByNameEquals(serviceName);
        // 根据服务ID，找到所有方法id
        long[] methodids = methodRepository.findDistinctIdByServiceidEquals(serviceid);
        for(long mid:methodids){
            List<Method> aloneMethod = new LinkedList<Method>();
            //根据方法id，找到前30条数据
            List<Monitor_method> methods = methodRepository.findTop30ByIdOrderByColumnidDesc(mid);
            for(Monitor_method monitorMethod:methods){
                Method method = Method.generate(monitorMethod);
                aloneMethod.add(method);
            }
            serviceMethods.add(aloneMethod);
        }
        return serviceMethods;
    }

}
