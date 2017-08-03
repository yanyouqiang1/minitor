package app;

import app.webInterface.RestInter;
import app.webInterface.entity.observation.*;
import app.webInterface.entity.setting.CommonReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/observation/servicesTopology",method = RequestMethod.GET)
    public RestServiceTopology getRestServiceTopology(){
        return restInter.getServiceTopology();
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

    @RequestMapping(value = "/setting/service/{serviceName}/webhook/up",method = RequestMethod.PUT)
    public CommonReply setWebhookUp(@PathVariable(name = "serviceName",required = true)String serviceName, @RequestParam(name = "webhookUp",required = false,defaultValue = "")String webhookUp){
        return restInter.setWebhookUp(serviceName,webhookUp);
    }
    @RequestMapping(value = "/setting/service/{serviceName}/webhook/down",method = RequestMethod.PUT)
    public CommonReply setWebhookDown(@PathVariable(name = "serviceName",required = true)String serviceName, @RequestParam(name = "webhookDown",required = false,defaultValue = "")String webhookDown){
        return restInter.setWebhookDown(serviceName,webhookDown);
    }

    @RequestMapping(value = "/setting/method/{methodId}/threshold/upper",method = RequestMethod.PUT)
    public CommonReply setMethodThresholdUpper(@PathVariable(name = "methodId",required = true)Long methodId, @RequestParam(name = "upper",required = true)int upper){
        return restInter.setMethodThresholdUpper(methodId,upper);
    }

    @RequestMapping(value = "/setting/method/{methodId}/threshold/lower",method = RequestMethod.PUT)
    public CommonReply setMethodThresholdLower(@PathVariable(name = "methodId",required = true)Long methodId, @RequestParam(name = "lower",required = true)int lower){
        return restInter.setMethodThresholdLower(methodId,lower);
    }
}
