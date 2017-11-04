//package app.outerInterface;
//
//import app.database.service.StrategyTimePeriodService;
//import app.outerInterface.RestInter;
//import app.outerInterface.entity.observation.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
///**
// * Created by Administrator on 2017/7/22 0022.
// */
//@RestController
//public class RestControl extends Re{
//
//    @Autowired
//    RestInter restInter;
//
//    @RequestMapping(value = "/observation/services",method = RequestMethod.GET)
//    public RestServices getRestService(){
//        return restInter.getRestService();
//    }
//
//    @RequestMapping(value = "/observation/service/{serviceName}/response",method = RequestMethod.GET)
//    public RestServiceResponse getRestServiceResponse(@PathVariable(name = "serviceName",required = true)String serviceName){
//        return restInter.getRestServiceResponse(serviceName);
//    }
//    @RequestMapping(value = "/observation/service/{serviceName}/container",method = RequestMethod.GET)
//    public RestServiceContainer getRestServiceContainer(@PathVariable(name = "serviceName",required = true)String serviceName){
//        return restInter.getRestServiceContainer(serviceName);
//    }
//    @RequestMapping(value = "/observation/service/{serviceName}/container/cpu",method = RequestMethod.GET)
//    public RestServiceContainerCpu getRestServiceContainerCpu(@PathVariable(name = "serviceName",required = true)String serviceName){
//        return restInter.getRestServiceContainerCpu(serviceName);
//    }
//    @RequestMapping(value = "/observation/service/{serviceName}/container/memory",method = RequestMethod.GET)
//    public RestServiceContainerMemory getRestServiceContainerMemory(@PathVariable(name = "serviceName",required = true)String serviceName){
//        return restInter.getRestServiceContainerMemory(serviceName);
//    }
//
//
//    @Autowired
//    StrategyTimePeriodService timePeriodService;
//
//    @RequestMapping("/strategy/serviceTimePeriod/update")
//    public boolean ServiceTimePeriod(@RequestParam(name = "service") String service, @RequestParam(name = "peek") String peek, @RequestParam(name = "though") String though){
//        timePeriodService.insertStrategy(service,peek,though);
//        return true;
//    }
//}
