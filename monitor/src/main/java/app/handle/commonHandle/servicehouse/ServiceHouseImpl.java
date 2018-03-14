package app.handle.commonHandle.servicehouse;

import app.database.service.ServiceSumupService;
import com.netflix.discovery.converters.Auto;
import entitylib.ServiceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceHouseImpl implements ServiceHouseInter {
    Map<String,Long> serviceMap = new HashMap<>();

    public ServiceHouseImpl() {
        init();
    }

    private void init(){
        serviceMap.put("ServiceA",0l);
        serviceMap.put("ServiceB",0l);
        serviceMap.put("ServiceC",0l);
        serviceMap.put("ServiceD",0l);
    }

    @Override
    public void messageReceive(ServiceMessage serviceMessage) {
        Long count = serviceMap.get(serviceMessage.getServiceName());
        if(count==null){
            serviceMap.put(serviceMessage.getServiceName(),Long.parseLong(serviceMessage.getCount()+""));
        }else{
            count += serviceMessage.getCount();
            serviceMap.put(serviceMessage.getServiceName(),count);
        }
    }

    @Autowired
    ServiceSumupService sumupService;

    @Override
    public void sumup() {
        sumupService.save(serviceMap);
        serviceMap.clear();
        init();
    }
}
