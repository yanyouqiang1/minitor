package app.handle.commonHandle.warehouse;

import entitylib.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
public abstract class AbstractGroupStatistics {
    //统计属性
    protected long visitors;
    protected float rate;
    protected String commonName;
    protected String springName;

    //成员属性
    @Autowired
    List<AbstractMethodStatistics> methodStatistics;

    //消息统计器
    private GroupStatisticsHandler statisticsHandler;

    //收到消息处理
    public void msgRecive(MsgEntity msgEntity){
        if(methodStatistics!=null) {
            Iterator iterator = methodStatistics.iterator();
            while (iterator.hasNext()) {
                AbstractMethodStatistics method = (AbstractMethodStatistics) iterator.next();
                method.msgRecive(msgEntity);
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
        this.commonName="";
        this.springName ="";
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

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getSpringName() {
        return springName;
    }

    public void setSpringName(String springName) {
        this.springName = springName;
    }

    public void setStatisticsHandler(GroupStatisticsHandler statisticsHandler) {
        this.statisticsHandler = statisticsHandler;
    }
}
