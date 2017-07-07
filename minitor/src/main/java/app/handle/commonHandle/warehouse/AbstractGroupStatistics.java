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
public abstract class AbstractGroupStatistics {
    //统计属性
    protected long visitors;
    protected float rate;
    protected String popularName="";
    protected String name="";
    protected String groupName="";

    //成员属性
    List<AbstractMethodStatistics> methodStatistics = new LinkedList<AbstractMethodStatistics>();
    private AbstractOverallStatistics parentOverall;
    //消息统计器
    private GroupStatisticsHandler statisticsHandler;

    //收到消息处理
    public void msgRecive(MsgEntity msgEntity){
        if(methodStatistics!=null) {
            boolean isSend =false;
            Iterator iterator = methodStatistics.iterator();
            while (iterator.hasNext()&&!isSend) {
                AbstractMethodStatistics method = (AbstractMethodStatistics) iterator.next();
                if(method.getResource().equals(msgEntity.getResouce())){
                    method.msgRecive(msgEntity);
                    isSend = true;
                }
            }
            if(!isSend){
                AbstractMethodStatistics method = SpringUtil.getBean(AbstractMethodStatistics.class);
                method.setResource(msgEntity.getResouce());
                method.setParentGroup(this);
                method.msgRecive(msgEntity);
                methodStatistics.add(method);
            }
        }
        update(msgEntity);
    }

    public abstract void update(MsgEntity msgEntity);

    public void sumup(){
        if(statisticsHandler!=null){
            statisticsHandler.handle(this);
        }
        if(methodStatistics!=null) {
            Iterator iterator = methodStatistics.iterator();
            while (iterator.hasNext()) {
                AbstractMethodStatistics method = (AbstractMethodStatistics) iterator.next();
                method.sumup();
            }
        }
        clear();
    }

    private void clear(){
        this.visitors = 0l;
        this.rate = 0f;
        this.popularName="";
        this.name ="";
        this.groupName="";
        methodStatistics.clear();
    }




    public Long getVisitors() {
        return visitors;
    }

    public void setVisitors(Long visitors) {
        this.visitors = visitors;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getPopularName() {
        return popularName;
    }

    public void setPopularName(String popularName) {
        this.popularName = popularName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatisticsHandler(GroupStatisticsHandler statisticsHandler) {
        this.statisticsHandler = statisticsHandler;
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
}
