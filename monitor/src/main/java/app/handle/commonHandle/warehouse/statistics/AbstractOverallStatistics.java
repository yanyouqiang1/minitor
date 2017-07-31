package app.handle.commonHandle.warehouse.statistics;

import app.util.SpringUtil;
import entitylib.MonitorMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/7/6.
 */
public abstract class AbstractOverallStatistics implements Statistics {
    //统计属性
    protected long visitors;

    @Autowired
    TopologyInter toplogyInter;

    //成员属性
    private Map<Long, AbstractGroupStatistics> groupMap;
    Map<Long, AbstractServiceStatistics> serviceStatisticsMap;

    //收到新的消息处理
    public void msgRecive(MonitorMessage monitorMessage) {
        if (groupMap == null || serviceStatisticsMap == null) {
            statisticsUpdate();
        }
        //下发到组
        Long groupid = monitorMessage.getGroupId();
        AbstractGroupStatistics group = groupMap.get(groupid);
        if (group != null) {
            group.msgRecive(monitorMessage);
        }
        //下发到服务
        long serviceid = monitorMessage.getServiceId();
        AbstractServiceStatistics service = serviceStatisticsMap.get(serviceid);
        if (service != null) {
            service.msgRecive(monitorMessage);
        }

        this.visitors++;
        update(monitorMessage);
    }

    //更新信息
    public abstract void update(MonitorMessage monitorMessage);

    //统计
    public void sumup() {
        handleResult(this);
        //组统计
        for (AbstractGroupStatistics group : groupMap.values()) {
            group.sumup();
        }

        //服务统计
        if (serviceStatisticsMap != null) {
            for (AbstractServiceStatistics service : serviceStatisticsMap.values()) {
                service.sumup();
            }
        }
        clear();
    }

    public abstract void handleResult(AbstractOverallStatistics overall);

    //清空
    public void clear() {
        visitors = 0l;
        attributeClear();
    }

    public abstract void attributeClear();

    public void statisticsUpdate() {
        toplogyInter.update();
        if (groupMap == null) {
            groupMap = new ConcurrentHashMap<>();
        }
        if (serviceStatisticsMap == null) {
            serviceStatisticsMap = new ConcurrentHashMap<>();
        }
        serviceStatisticsMap.clear();
        groupMap.clear();
        Map<Long, String> groups = toplogyInter.getAllGroups();
        if (groups != null) {
            for (Map.Entry<Long, String> entry : groups.entrySet()) {
                AbstractGroupStatistics group = SpringUtil.getBean(AbstractGroupStatistics.class);
                group.setName(entry.getValue());
                group.setId(entry.getKey());
                group.setParentOverall(this);
                group.statisticsUpdate();
                groupMap.put(entry.getKey(), group);
            }
        }
        Map<Long, String> services = toplogyInter.getAllServices();
        if (services != null) {
            for (Map.Entry<Long, String> serviceEntry : services.entrySet()) {
                AbstractServiceStatistics service = SpringUtil.getBean(AbstractServiceStatistics.class);
                service.setId(serviceEntry.getKey());
                service.setName(serviceEntry.getValue());
                service.statisticsUpdate();
                serviceStatisticsMap.put(serviceEntry.getKey(), service);
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
