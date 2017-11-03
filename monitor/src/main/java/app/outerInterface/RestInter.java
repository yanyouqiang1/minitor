package app.outerInterface;

import app.outerInterface.entity.list.Details;
import app.outerInterface.entity.list.Details_group;
import app.outerInterface.entity.list.Details_resources;
import app.outerInterface.entity.list.Details_services;
import app.outerInterface.entity.method.*;
import app.outerInterface.entity.overview.*;

/**
 * Created by Administrator on 2017/7/10.
 */
public interface RestInter {
    public Overview overview();

    public Overview_visitors overview_visitors();

    public Overview_success_rate overview_success_rate();

    public Overview_status_code overview_status_code();

    public Overview_response_time overview_response_time();

    public Overview_tps overview_tps();

    public Details details(int page,int size);

    public Details_group details_group(long groupid,int page,int size);

    public Details_resources details_resources(long resourceid,int page,int size);

    public Details_services details_services(long serviceid,int page,int size);

    public Method method(long id);

    public Method_visitors method_visitors(long id);

    public Method_success_rate method_success_rate(long id);

    public Method_status_code method_status_code(long id);

    public Method_response_time method_response_time(long id);

    public Method_tps method_tps(long id);

}
