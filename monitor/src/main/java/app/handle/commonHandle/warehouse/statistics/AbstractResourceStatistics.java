package app.handle.commonHandle.warehouse.statistics;

import app.Global;
import app.util.SpringUtil;
import entitylib.MsgEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/11.
 */
public abstract class AbstractResourceStatistics implements Statistics{
    //统计属性
    long visitors;
    //自身属性
    long id;
    String name;

    //映射属性
    protected AbstractGroupStatistics parentGroup;
    Map<Long,AbstractMethodStatistics> methodStatisticsMap;

    public void msgRecive(MsgEntity msgEntity){
        Long methodid = msgEntity.getMethodid();
        if(methodStatisticsMap==null){
            statisticsUpdate();
        }
        AbstractMethodStatistics method =  methodStatisticsMap.get(methodid);
        if(method!=null){
            method.msgRecive(msgEntity);
        }
        this.visitors++;
        update(msgEntity);
    }
    public abstract void update(MsgEntity msgEntity);
    public void sumup(){
        handleResult(this);
        if(methodStatisticsMap!=null){
            for(AbstractMethodStatistics method:methodStatisticsMap.values()){
                method.sumup();
            }
        }
        clear();

    }
    public abstract void handleResult(AbstractResourceStatistics resource);

    public void clear() {
        this.visitors=0;
        attributeClear();
    }
    public abstract void attributeClear();
    public void statisticsUpdate() {
        if(methodStatisticsMap==null){
            methodStatisticsMap = new HashMap<>();
        }
        methodStatisticsMap.clear();
        Map<Long,String> resources = Global.getMethodByResourceId(this.id);
        if(resources!=null){
            for(Map.Entry<Long,String> resourceEntry:resources.entrySet()){
                AbstractMethodStatistics method = SpringUtil.getBean(AbstractMethodStatistics.class);
                method.setId(resourceEntry.getKey());
                method.setName(resourceEntry.getValue());
                method.setParentResource(this);
                methodStatisticsMap.put(resourceEntry.getKey(),method);
            }
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractGroupStatistics getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(AbstractGroupStatistics parentGroup) {
        this.parentGroup = parentGroup;
    }

    public long getVisitors() {
        return visitors;
    }

    public void setVisitors(long visitors) {
        this.visitors = visitors;
    }
}
