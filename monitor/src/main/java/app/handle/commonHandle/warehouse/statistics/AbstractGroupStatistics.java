package app.handle.commonHandle.warehouse.statistics;

import app.handle.commonHandle.warehouse.statistics.gateway.HandleResource;
import app.handle.commonHandle.warehouse.statistics.gateway.TopologyInter;
import app.util.SpringUtil;
import entitylib.RequestMessage;
import entitylib.ResponseMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/7/6.
 */
public abstract class AbstractGroupStatistics implements Statistics{
    @Autowired
    TopologyInter toplogyInter;

    //统计属性
    @Getter
    protected long response_visitors,request_visitors;

    //自身属性
    protected String name="";
    protected Long id;


    //映射属性
    Map<Long,AbstractResourceStatistics> resourceStatisticsMap;
    private AbstractOverallStatistics parentOverall;

    //收到消息处理
    public void msgReceive(ResponseMessage responseMessage){
        long resourceid = responseMessage.getResourceId();
        if(resourceStatisticsMap==null){
            statisticsUpdate();
        }
        AbstractResourceStatistics resource = resourceStatisticsMap.get(resourceid);
        if(resource!=null){
            resource.msgReceive(responseMessage);
        }
        this.response_visitors++;
        update(responseMessage);
    }

    @Override
    public void msgReceive(RequestMessage requestMessage) {
        long resourceid = requestMessage.getResourceId();
        if(resourceStatisticsMap==null){
            statisticsUpdate();
        }
        AbstractResourceStatistics resource = resourceStatisticsMap.get(resourceid);
        if(resource!=null){
            resource.msgReceive(requestMessage);
        }
        this.request_visitors++;
    }

    public abstract void update(ResponseMessage responseMessage);

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
        this.response_visitors = 0l;
        this.request_visitors = 0l;
        attributeClear();
    }
    public abstract void attributeClear();

    public void statisticsUpdate(){
        if(resourceStatisticsMap==null) {
            resourceStatisticsMap = new ConcurrentHashMap<>();
        }
        resourceStatisticsMap.clear();
        List<HandleResource> handleResources = toplogyInter.getResourceByGroupId(this.id);
        if(handleResources!=null){
            for(HandleResource handleResource:handleResources){
                AbstractResourceStatistics resource = SpringUtil.getBean(AbstractResourceStatistics.class);
                resource.setId(handleResource.getId());
                resource.setName(handleResource.getResourceName());
                resource.setServiceId(handleResource.getServiceId());
                resource.setParentGroup(this);
                resource.statisticsUpdate();
                resourceStatisticsMap.put(handleResource.getId(),resource);
            }
        }
//        Map<Long,String> resources = toplogyInter.getResourceByGroupId(this.id);
//        if(resources!=null){
//            for(Map.Entry<Long,String> resourceEntry:resources.entrySet()){
//                AbstractResourceStatistics resource = SpringUtil.getBean(AbstractResourceStatistics.class);
//                resource.setId(resourceEntry.getKey());
//                resource.setName(resourceEntry.getValue());
//                resource.setParentGroup(this);
//                resource.statisticsUpdate();
//                resourceStatisticsMap.put(resourceEntry.getKey(),resource);
//            }
//        }
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
