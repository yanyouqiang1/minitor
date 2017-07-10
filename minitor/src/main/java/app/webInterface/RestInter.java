package app.webInterface;

import app.webInterface.entity.list.Details;
import app.webInterface.entity.list.Details_group;
import app.webInterface.entity.list.Details_resources;
import app.webInterface.entity.list.Details_services;
import app.webInterface.entity.method.*;
import app.webInterface.entity.overview.*;

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

    public Details_group details_group(int groupid,int page,int size);

    public Details_resources details_resources(int resourceid,int page,int size);

    public Details_services details_services(int serviceid,int page,int size);

    public Method method(int id);

    public Method_visitors method_visitors(int id);

    public Method_success_rate method_success_rate(int id);

    public Method_status_code method_status_code(int id);

    public Method_response_time method_response_time(int id);

    public Method_tps method_tps(int id);

}
