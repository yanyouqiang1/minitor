package app.handle.commonHandle.warehouse.statistics;

import app.Global;
import app.util.SpringUtil;
import entitylib.MsgEntity;

import java.util.*;

/**
 * Created by Administrator on 2017/7/6.
 */
public abstract class AbstractOverallStatistics implements Statistics{
    //统计属性
    protected long visitors;


    //成员属性
    private Map<Long,AbstractGroupStatistics> groupMap;


    //收到新的消息处理
    public void msgRecive(MsgEntity msgEntity){
        if(groupMap==null) {
            statisticsUpdate();
        }
        Long groupid = msgEntity.getGroupid();
        AbstractGroupStatistics group = groupMap.get(groupid);
        if(group!=null){
            group.msgRecive(msgEntity);
        }
        this.visitors++;
        update(msgEntity);
    }
    //更新信息
    public abstract void update(MsgEntity msgEntity);

    //统计
    public void sumup(){
        for(AbstractGroupStatistics group: groupMap.values()){
            group.sumup();
        }
        handleResult(this);
        clear();
    }

    public abstract void handleResult(AbstractOverallStatistics overall);

    //清空
    public void clear(){
        visitors = 0l;
        attributeClear();
    }
    public abstract void attributeClear();

    public void statisticsUpdate() {
        if(groupMap==null){
            groupMap = new HashMap<>();
        }
        groupMap.clear();
        Map<Long,String> groups = Global.getGroups();
        if(groups!=null){
            for(Map.Entry<Long,String> entry:groups.entrySet()){
                AbstractGroupStatistics group = SpringUtil.getBean(AbstractGroupStatistics.class);
                group.setGroupName(entry.getValue());
                group.setGroupid(entry.getKey());
                group.setParentOverall(this);
                group.statisticsUpdate();
                groupMap.put(entry.getKey(),group);
            }
        }
    }

    public Long getVisitors() {
        return visitors;
    }

    public void setVisitors(Long visitors) {
        this.visitors = visitors;
    }
}
