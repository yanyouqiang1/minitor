package app.database.service;

import app.database.dao.ServiceRepository;
import app.database.domain.Monitor_services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */
@Service
public class ServiceService {
    @Autowired
    ServiceRepository serviceRepository;

    /***
     *  获得每个服务90个数据
     * @return
     */
    public List<List<Monitor_services>> getTop90GroupService(){
        List<List<Monitor_services>> groupService = new LinkedList<List<Monitor_services>>();
        List<Long> ids = serviceRepository.findDistinctIdByColumnidNotNull();
        if(ids!=null){
            for(Long id:ids){
                List<Monitor_services> servicesList = serviceRepository.findTop90ByIdEquals(id);
                groupService.add(servicesList);
            }
        }
        return groupService;
    }

    public List<Monitor_services> getTop90ByServiceName(String serviceName){
        return serviceRepository.findTop90ByNameEqualsOrderByColumnidDesc(serviceName);
    }

    public long getLatestRequestVisitor(String serviceName){
        return 0;
    }
}
