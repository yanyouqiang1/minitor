package app.webInterface;

import app.handle.commonHandle.GlobalResource;
import app.database.dao.*;
import app.database.domain.*;
import app.handle.commonHandle.warehouse.statistics.gateway.TopologyInter;
import app.webInterface.entity.list.Details;
import app.webInterface.entity.list.Details_group;
import app.webInterface.entity.list.Details_resources;
import app.webInterface.entity.list.Details_services;
import app.webInterface.entity.method.*;
import app.webInterface.entity.overview.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/10.
 */
@Component
public class RestImpl implements RestInter {
    @Autowired
    OverallRepository overallRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    MethodRepository methodRepository;

    @Autowired
    TopologyInter topologyInter;

    @Override
    public Overview overview() {
        Monitor_overall overall = GlobalResource.getCurrentOverall();
        List<Monitor_group> monitor_groups = groupRepository.findAllByOverall(overall);
        Overview overview = new Overview();
        overview.generate(overall,monitor_groups);
        return overview;
    }

    @Override
    public Overview_visitors overview_visitors() {
        List<Monitor_overall> overalls = overallRepository.findTop90ByIdIsNotNullOrderByIdDesc();
        Overview_visitors visitors = new Overview_visitors();
        visitors.generate(overalls);
        return visitors;
    }

    @Override
    public Overview_success_rate overview_success_rate() {
        List<Monitor_overall> overalls = overallRepository.findTop90ByIdIsNotNullOrderByIdDesc();
        Overview_success_rate success_rate = new Overview_success_rate();
        success_rate.generate(overalls);
        return success_rate;
    }

    @Override
    public Overview_status_code overview_status_code() {
        List<Monitor_overall> overalls = overallRepository.findTop90ByIdIsNotNullOrderByIdDesc();
        Overview_status_code status_code = new Overview_status_code();
        status_code.generate(overalls);
        return status_code;
    }

    @Override
    public Overview_response_time overview_response_time() {
        List<Monitor_overall> overalls = overallRepository.findTop90ByIdIsNotNullOrderByIdDesc();
        Overview_response_time response_time = new Overview_response_time();
        response_time.generate(overalls);
        return response_time;
    }

    @Override
    public Overview_tps overview_tps() {
        List<Monitor_overall> overalls = overallRepository.findTop90ByIdIsNotNullOrderByIdDesc();
        Overview_tps tps = new Overview_tps();
        tps.generate(overalls);
        return tps;
    }

    @Override
    public Details details(int page, int size) {
        List<Monitor_method> monitor_methods = methodRepository.findAllByOverall(new PageRequest(page,size), GlobalResource.getCurrentOverall());
        Details details = new Details();
        details.generate(monitor_methods);
        return details;
    }

    @Override
    public Details_group details_group(long groupid, int page, int size) {
        Map groupMap = topologyInter.getAllGroups();
        //查询的group不存在，返回空
        if(groupMap.get(groupid)==null){
            return null;
        }
        List<Monitor_method> monitor_methods = methodRepository.findAllByGroupidAndOverall(new PageRequest(page,size),groupid, GlobalResource.getCurrentOverall());
        String groupName = groupMap.get(groupid).toString();
        Details_group details_group = new Details_group();
        details_group.generate(monitor_methods,groupName,groupid);
        return details_group;
    }

    @Override
    public Details_resources details_resources(long resourceid, int page, int size) {
        List<Monitor_method> monitor_methods = methodRepository.findByResourceIdAndOverall(new PageRequest(page,size),resourceid, GlobalResource.getCurrentOverall());
        String resourceName = resourceRepository.findDistinctNameById(resourceid);
        Details_resources details_resources = new Details_resources();
        details_resources.generate(monitor_methods,resourceName,resourceid);
        return details_resources;
    }

    @Override
    public Details_services details_services(long serviceid, int page, int size) {
        List<Monitor_method> monitor_methods = methodRepository.findAllByServiceidAndOverall(new PageRequest(page,size),serviceid, GlobalResource.getCurrentOverall());
        String serviceName = serviceRepository.findDistinctNameById(serviceid);
        Details_services details_services = new Details_services();
        details_services.generate(monitor_methods,serviceName,serviceid);
        return details_services;
    }

    @Override
    public Method method(long id) {
        Monitor_method monitor_method = methodRepository.findFirstByIdOrderByColumnid(id);
        Method method = new Method();
        method.generate(monitor_method);
        return method;
    }

    @Override
    public Method_visitors method_visitors(long id) {
        List<Monitor_method> monitor_method = methodRepository.findTop90ByIdOrderByColumnid(id);
        Method_visitors visitors = new Method_visitors();
        visitors.generate(monitor_method);
        return visitors;
    }

    @Override
    public Method_success_rate method_success_rate(long id) {
        List<Monitor_method> monitor_method = methodRepository.findTop90ByIdOrderByColumnid(id);
        Method_success_rate success_rate = new Method_success_rate();
        success_rate.generate(monitor_method);
        return success_rate;
    }

    @Override
    public Method_status_code method_status_code(long id) {
        List<Monitor_method> monitor_method = methodRepository.findTop90ByIdOrderByColumnid(id);
        Method_status_code status_code = new Method_status_code();
        status_code.generate(monitor_method);
        return status_code;
    }

    @Override
    public Method_response_time method_response_time(long id) {
        List<Monitor_method> monitor_method = methodRepository.findTop90ByIdOrderByColumnid(id);
        Method_response_time response_time = new Method_response_time();
        response_time.generate(monitor_method);
        return response_time;
    }

    @Override
    public Method_tps method_tps(long id) {
        List<Monitor_method> monitor_method = methodRepository.findTop90ByIdOrderByColumnid(id);
        Method_tps tps = new Method_tps();
        tps.generate(monitor_method);
        return tps;
    }
}
