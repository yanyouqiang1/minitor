package app.handle.commonHandle.warehouse.statistics;

import app.Global;
import app.util.SpringUtil;
import entitylib.MsgEntity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/7/6.
 */
public abstract class AbstractGroupStatistics implements Statistics{
    //统计属性
    protected long visitors;

    //自身属性
    protected String name="";
    protected Long id;


    //映射属性
    Map<Long,AbstractResourceStatistics> resourceStatisticsMap;
    private AbstractOverallStatistics parentOverall;

    //收到消息处理
    public void msgRecive(MsgEntity msgEntity){
        long resourceid = msgEntity.getResourceId();
        if(resourceStatisticsMap==null){
            statisticsUpdate();
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
        handleResult(this);
        if(resourceStatisticsMap!=null){
            for(AbstractResourceStatistics resource:resourceStatisticsMap.values()){
                resource.sumup();
            }
        }
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
            resourceStatisticsMap = new ConcurrentHashMap<>();
        }
        resourceStatisticsMap.clear();
        Map<Long,String> resources = Global.getResoucesByGroupId(this.id);
        if(resources!=null){
            for(Map.Entry<Long,String> resourceEntry:resources.entrySet()){
                AbstractResourceStatistics resource = SpringUtil.getBean(AbstractResourceStatistics.class);
                resource.setId(resourceEntry.getKey());
                resource.setName(resourceEntry.getValue());
                resource.setParentGroup(this);
                resource.statisticsUpdate();
                resourceStatisticsMap.put(resourceEntry.getKey(),resource);
            }
        }
    }


    public Long getVisitors() {
        return visitors;
    }

    public void setVisitors(Long visitors) {
        this.visitors = visitors;
    }


    public AbstractOverallStatistics getParentOverall() {
        return parentOverall;
    }

    public void setParentOverall(AbstractOverallStatistics parentOverall) {
        this.parentOverall = parentOverall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
