package app;

import app.webInterface.RestInter;
import app.webInterface.entity.observation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/7/22 0022.
 */
@RestController
public class RestControl {

    @Autowired
    RestInter restInter;

    @RequestMapping(value = "/observation/services",method = RequestMethod.GET)
    public RestServices getRestService(){
        return restInter.getRestService();
    }

    @RequestMapping(value = "/observation/service/{serviceName}/response",method = RequestMethod.GET)
    public RestServiceResponse getRestServiceResponse(@PathVariable(name = "serviceName",required = true)String serviceName){
        return restInter.getRestServiceResponse(serviceName);
    }
    @RequestMapping(value = "/observation/service/{serviceName}/container",method = RequestMethod.GET)
    public RestServiceContainer getRestServiceContainer(@PathVariable(name = "serviceName",required = true)String serviceName){
        return restInter.getRestServiceContainer(serviceName);
    }
    @RequestMapping(value = "/observation/service/{serviceName}/container/cpu",method = RequestMethod.GET)
    public RestServiceContainerCpu getRestServiceContainerCpu(@PathVariable(name = "serviceName",required = true)String serviceName){
        return restInter.getRestServiceContainerCpu(serviceName);
    }
    @RequestMapping(value = "/observation/service/{serviceName}/container/memory",method = RequestMethod.GET)
    public RestServiceContainerMemory getRestServiceContainerMemory(@PathVariable(name = "serviceName",required = true)String serviceName){
        return restInter.getRestServiceContainerMemory(serviceName);
    }


}
