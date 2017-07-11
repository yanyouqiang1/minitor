package app.handle.commonHandle.warehouse.statistics;

import app.Global;
import app.util.SpringUtil;
import entitylib.MsgEntity;

import java.util.*;

/**
 * Created by Administrator on 2017/7/6.
 */
public abstract class AbstractGroupStatistics implements Statistics{
    //统计属性
    protected long visitors;

    //自身属性
    protected String groupName="";
    protected Long groupid;


    //映射属性
    Map<Long,AbstractResourceStatistics> resourceStatisticsMap;
    Map<Long,AbstractServiceStatistics> serviceStatisticsMap;
    private AbstractOverallStatistics parentOverall;

    //收到消息处理
    public void msgRecive(MsgEntity msgEntity){
        long serviceid = msgEntity.getServiceid();
        long resourceid = msgEntity.getResouceid();
        if(serviceStatisticsMap==null||resourceStatisticsMap==null){
            statisticsUpdate();
        }
        AbstractServiceStatistics service = serviceStatisticsMap.get(serviceid);
        if(service!=null){
            service.msgRecive(msgEntity);
        }
        AbstractResourceStatistics resource = resourceStatisticsMap.get(resourceid);
        if(resource!=null){
            resource.msgRecive(msgEntity);
        }
        this.visitors++;
        update(msgEntity);
    }

    public abstract void update(MsgEntity msgEntity);

    public void sumup(){
        if(resourceStatisticsMap!=null){
            for(AbstractResourceStatistics resource:resourceStatisticsMap.values()){
                resource.sumup();
            }
        }
        if(serviceStatisticsMap!=null){
            for(AbstractServiceStatistics service:serviceStatisticsMap.values()){
                service.sumup();
            }
        }
        handleResult(this);
        clear();
    }
    public abstract void handleResult(AbstractGroupStatistics group);

    public void clear(){
        this.visitors = 0l;
        attributeClear();
    }
    public abstract void attributeClear();

    public void statisticsUpdate(){
        if(resourceStatisticsMap==null) {
            resourceStatisticsMap = new HashMap<>();
        }
        if(serviceStatisticsMap==null){
            serviceStatisticsMap = new HashMap<>();
        }
        resourceStatisticsMap.clear();
        serviceStatisticsMap.clear();
        Map<Long,String> resources = Global.getResoucesByGroupId(this.groupid);
        if(resources!=null){
            for(Map.Entry<Long,String> resourceEntry:resources.entrySet()){
                AbstractResourceStatistics resource = SpringUtil.getBean(AbstractResourceStatistics.class);
                resource.setResourceid(resourceEntry.getKey());
                resource.setName(resourceEntry.getValue());
                resource.setParentGroup(this);
                resource.statisticsUpdate();
                resourceStatisticsMap.put(resourceEntry.getKey(),resource);
            }
        }
        Map<Long,String> services = Global.getServices();
        if(services!=null){
            for(Map.Entry<Long,String> serviceEntry:services.entrySet()){
                AbstractServiceStatistics service = SpringUtil.getBean(AbstractServiceStatistics.class);
                service.setServiceid(serviceEntry.getKey());
                service.setName(serviceEntry.getValue());
                service.statisticsUpdate();
                serviceStatisticsMap.put(serviceEntry.getKey(),service);
            }
        }
    }


    public Long getVisitors() {
        return visitors;
    }

    public void setVisitors(Long visitors) {
        this.visitors = visitors;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public AbstractOverallStatistics getParentOverall() {
        return parentOverall;
    }

    public void setParentOverall(AbstractOverallStatistics parentOverall) {
        this.parentOverall = parentOverall;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }
}
