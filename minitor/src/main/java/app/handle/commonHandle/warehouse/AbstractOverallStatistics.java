package app.handle.commonHandle.warehouse;

import app.handle.util.SpringUtil;
import entitylib.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
public abstract class AbstractOverallStatistics {
    //统计属性
    protected long visitors;

    protected long method_get,method_post,method_put,method_delete;

    protected long status_200,status_300,status_400,status_500;

    protected int response_min,response_max,resposne_avg;

    protected long TPS;


    //成员属性
    private List<AbstractGroupStatistics> groupStatistics = new LinkedList<AbstractGroupStatistics>();


    //定义处理器
    private OverallStatisticsHandler overallStatisticsHandler;

    //收到新的消息处理
    public void msgRecive(MsgEntity msgEntity){
        if(groupStatistics!=null) {
            boolean isSend=false;
            Iterator iterator = groupStatistics.iterator();
            while (iterator.hasNext()&&!isSend) {
                AbstractGroupStatistics group = (AbstractGroupStatistics) iterator.next();
                if(group.getGroupName().equals(msgEntity.getGroup())){
                    group.msgRecive(msgEntity);
                    isSend = true;
                }
            }
            if(!isSend){
                AbstractGroupStatistics group = SpringUtil.getBean(AbstractGroupStatistics.class);
                group.setGroupName(msgEntity.getGroup());
                group.msgRecive(msgEntity);
                groupStatistics.add(group);
            }
        }
        update(msgEntity);
    }

    //更新信息
    public abstract void update(MsgEntity msgEntity);


    //统计
    public void sumup(){
        if(overallStatisticsHandler!=null){
            overallStatisticsHandler.handle(this);
        }
        if(groupStatistics!=null) {
            Iterator iterator = groupStatistics.iterator();
            while (iterator.hasNext()) {
                AbstractGroupStatistics group = (AbstractGroupStatistics) iterator.next();
                group.sumup();
            }
        }
        clear();
    }


    //清空
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
        groupStatistics.clear();
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

    public long getTPS() {
        return TPS;
    }

    public void setTPS(long TPS) {
        this.TPS = TPS;
    }

    public void setOverallStatisticsHandler(OverallStatisticsHandler overallStatisticsHandler) {
        this.overallStatisticsHandler = overallStatisticsHandler;
    }
}
