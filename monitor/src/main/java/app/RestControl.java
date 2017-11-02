package app;

import app.database.dao.OverallRepository;
import app.database.service.MonitorMethodService;
import app.feignclient.GatewayManager;
import app.feignclient.entity.Group;
import app.handle.commonHandle.warehouse.statistics.gateway.TopologyInter;
import app.webInterface.RestInter;
import app.webInterface.ScheduleRestControl;
import app.webInterface.entity.list.Details;
import app.webInterface.entity.list.Details_group;
import app.webInterface.entity.list.Details_resources;
import app.webInterface.entity.list.Details_services;
import app.webInterface.entity.method.*;
import app.webInterface.entity.overview.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@RestController
public class RestControl extends ScheduleRestControl{
    @Autowired
    RestInter restInter;

    @RequestMapping("/monitor_overview")
    public Overview overview() {
        return restInter.overview();
    }

    @RequestMapping("/monitor_overview/visitors")
    public Overview_visitors overview_visitors() {
        return restInter.overview_visitors();
    }

    @RequestMapping("/monitor_overview/success_rate")
    public Overview_success_rate Overview_success_rate() {
        return restInter.overview_success_rate();
    }

    @RequestMapping("/monitor_overview/status_code")
    public Overview_status_code Overview_status_code() {
        return restInter.overview_status_code();
    }

    @RequestMapping("/monitor_overview/response_time")
    public Overview_response_time Overview_response_time() {
        return restInter.overview_response_time();
    }

    @RequestMapping("/monitor_overview/tps")
    public Overview_tps Overview_tps() {
        return restInter.overview_tps();
    }

    @RequestMapping("/monitor_details")
    public Details Details(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return restInter.details(page, size);
    }

    @RequestMapping("/groups/{id}/monitor_details")
    public Details_group Details_group(@PathVariable(name = "id") long id, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return restInter.details_group(id, page, size);
    }

    @RequestMapping("/resources/{id}/monitor_details")
    public Details_resources Details_resources(@PathVariable(name = "id") long id, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return restInter.details_resources(id, page, size);
    }

    @RequestMapping("/services/{id}/monitor_details")
    public Details_services Details_services(@PathVariable(name = "id") long id, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return restInter.details_services(id, page, size);
    }

    @RequestMapping("/methods/{id}/monitor_details")
    public Method Method(@PathVariable(name = "id") long id) {
        return restInter.method(id);
    }

    @RequestMapping("/methods/{id}/monitor_details/visitors")
    public Method_visitors Method_visitors(@PathVariable(name = "id") long id) {
        return restInter.method_visitors(id);
    }

    @RequestMapping("/methods/{id}/monitor_details/success_rate")
    public Method_success_rate Method_success_rate(@PathVariable(name = "id") long id) {
        return restInter.method_success_rate(id);
    }

    @RequestMapping("/methods/{id}/monitor_details/status_code")
    public Method_status_code Method_status_code(@PathVariable(name = "id") long id) {
        return restInter.method_status_code(id);
    }

    @RequestMapping("/methods/{id}/monitor_details/response_time")
    public Method_response_time Method_response_time(@PathVariable(name = "id") long id) {
        return restInter.method_response_time(id);
    }

    @RequestMapping("/methods/{id}/monitor_details/tps")
    public Method_tps Method_tps(@PathVariable(name = "id") long id) {
        return restInter.method_tps(id);
    }

    @Autowired
    TopologyInter topologyInter;
    @RequestMapping("/test")
    public String test2(){
        topologyInter.getMethodsByServiceName("123");
        List e = topologyInter.getMethodsByServiceName("ServiceA");
        return e.toString();
    }
}
