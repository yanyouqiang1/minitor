package app.handle.commonHandle.warehouse;

import entitylib.MsgEntity;

/**
 * Created by Administrator on 2017/7/6.
 */
public abstract class AbstractMethodStatistics {
    //统计属性
    protected long visitors;

    protected long method_get,method_post,method_put,method_delete;

    protected long status_200,status_300,status_400,status_500;

    protected int response_min,response_max,resposne_avg;

    protected int TPS;

    //消息处理器
    private MethodStatisticsHandler methodStatisticsHandler;

    public void msgRecive(MsgEntity msgEntity){
        update(msgEntity);
    }

    public abstract void update(MsgEntity msgEntity);

    public void sumup(){
        if(methodStatisticsHandler!=null){
            methodStatisticsHandler.handle(this);
        }
        clear();
    }


    public void clear(){
        visitors = 0l;
        method_get = 0l;
        method_post = 0l;
        method_put =0l;
        method_delete=0l;
        status_200 = 0l;
        status_300 =0l;
        status_400 = 0l;
        status_500=0l;
        response_min = 0;
        response_max =0;
        resposne_avg =0;
        TPS = 0;
    }

    public Long getVisitors() {
        return visitors;
    }

    public void setVisitors(Long visitors) {
        this.visitors = visitors;
    }

    public Long getMethod_get() {
        return method_get;
    }

    public void setMethod_get(Long method_get) {
        this.method_get = method_get;
    }

    public Long getMethod_post() {
        return method_post;
    }

    public void setMethod_post(Long method_post) {
        this.method_post = method_post;
    }

    public Long getMethod_put() {
        return method_put;
    }

    public void setMethod_put(Long method_put) {
        this.method_put = method_put;
    }

    public Long getMethod_delete() {
        return method_delete;
    }

    public void setMethod_delete(Long method_delete) {
        this.method_delete = method_delete;
    }

    public Long getStatus_200() {
        return status_200;
    }

    public void setStatus_200(Long status_200) {
        this.status_200 = status_200;
    }

    public Long getStatus_300() {
        return status_300;
    }

    public void setStatus_300(Long status_300) {
        this.status_300 = status_300;
    }

    public Long getStatus_400() {
        return status_400;
    }

    public void setStatus_400(Long status_400) {
        this.status_400 = status_400;
    }

    public Long getStatus_500() {
        return status_500;
    }

    public void setStatus_500(Long status_500) {
        this.status_500 = status_500;
    }

    public Integer getResponse_min() {
        return response_min;
    }

    public void setResponse_min(Integer response_min) {
        this.response_min = response_min;
    }

    public Integer getResponse_max() {
        return response_max;
    }

    public void setResponse_max(Integer response_max) {
        this.response_max = response_max;
    }

    public Integer getResposne_avg() {
        return resposne_avg;
    }

    public void setResposne_avg(Integer resposne_avg) {
        this.resposne_avg = resposne_avg;
    }

    public Integer getTPS() {
        return TPS;
    }

    public void setTPS(Integer TPS) {
        this.TPS = TPS;
    }

    public void setMethodStatisticsHandler(MethodStatisticsHandler methodStatisticsHandler) {
        this.methodStatisticsHandler = methodStatisticsHandler;
    }
}
