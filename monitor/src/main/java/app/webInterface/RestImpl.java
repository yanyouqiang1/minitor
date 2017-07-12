package app.webInterface;

import app.Global;
import app.database.dao.*;
import app.database.domain.Monitor_method;
import app.database.domain.Monitor_overall;
import app.webInterface.entity.list.Details;
import app.webInterface.entity.list.Details_group;
import app.webInterface.entity.list.Details_resources;
import app.webInterface.entity.list.Details_services;
import app.webInterface.entity.method.*;
import app.webInterface.entity.overview.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public Overview overview() {
        Monitor_overall overall = Global.CurrentOverall;
        Overview overview = new Overview();
        overview.generate(overall);
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
        return null;
    }

    @Override
    public Details_group details_group(int groupid, int page, int size) {
        return null;
    }

    @Override
    public Details_resources details_resources(int resourceid, int page, int size) {
        return null;
    }

    @Override
    public Details_services details_services(int serviceid, int page, int size) {
        return null;
    }

    @Override
    public Method method(int id) {
        Monitor_method monitor_method = methodRepository.findFirstByIdOrderByColumnid(id);
        Method method = new Method();
        method.generate(monitor_method);
        return method;
    }

    @Override
    public Method_visitors method_visitors(int id) {
        List<Monitor_method> monitor_method = methodRepository.findTop90ByIdOrderByColumnid(id);
        Method_visitors visitors = new Method_visitors();
        visitors.generate(monitor_method);
        return visitors;
    }

    @Override
    public Method_success_rate method_success_rate(int id) {
        List<Monitor_method> monitor_method = methodRepository.findTop90ByIdOrderByColumnid(id);
        Method_success_rate success_rate = new Method_success_rate();
        success_rate.generate(monitor_method);
        return success_rate;
    }

    @Override
    public Method_status_code method_status_code(int id) {
        List<Monitor_method> monitor_method = methodRepository.findTop90ByIdOrderByColumnid(id);
        Method_status_code status_code = new Method_status_code();
        status_code.generate(monitor_method);
        return status_code;
    }

    @Override
    public Method_response_time method_response_time(int id) {
        List<Monitor_method> monitor_method = methodRepository.findTop90ByIdOrderByColumnid(id);
        Method_response_time response_time = new Method_response_time();
        response_time.generate(monitor_method);
        return response_time;
    }

    @Override
    public Method_tps method_tps(int id) {
        List<Monitor_method> monitor_method = methodRepository.findTop90ByIdOrderByColumnid(id);
        Method_tps tps = new Method_tps();
        tps.generate(monitor_method);
        return tps;
    }
}
