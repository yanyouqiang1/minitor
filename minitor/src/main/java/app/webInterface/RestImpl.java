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
public class RestImpl implements RestInter {
    @Override
    public Overview overview() {
        return null;
    }

    @Override
    public Overview_visitors overview_visitors() {
        return null;
    }

    @Override
    public Overview_success_rate overview_success_rate() {
        return null;
    }

    @Override
    public Overview_status_code overview_status_code() {
        return null;
    }

    @Override
    public Overview_response_time overview_response_time() {
        return null;
    }

    @Override
    public Overview_tps overview_tps() {
        return null;
    }

    @Override
    public Details details(int page, int size) {
        return null;
    }

    @Override
    public Details_group details_group(int page, int size) {
        return null;
    }

    @Override
    public Details_resources details_resources(int page, int size) {
        return null;
    }

    @Override
    public Details_services details_services(int page, int size) {
        return null;
    }

    @Override
    public Method method() {
        return null;
    }

    @Override
    public Method_visitors method_visitors() {
        return null;
    }

    @Override
    public Method_success_rate method_success_rate() {
        return null;
    }

    @Override
    public Method_status_code method_status_code() {
        return null;
    }

    @Override
    public Method_response_time method_response_time() {
        return null;
    }

    @Override
    public Method_tps method_tps() {
        return null;
    }
}
