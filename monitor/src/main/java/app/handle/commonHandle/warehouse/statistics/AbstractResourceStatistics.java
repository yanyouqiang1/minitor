package app.handle.commonHandle.warehouse.statistics;

import app.util.SpringUtil;
import entitylib.MonitorMessage;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/7/11.
 */
@Data
public abstract class AbstractResourceStatistics implements Statistics {
    @Autowired
    TopologyInter toplogyInter;

    //统计属性
    protected long visitors;
    //自身属性
    protected Long id;
    protected String name;

    //映射属性
    protected AbstractGroupStatistics parentGroup;
    Map<Long, AbstractMethodStatistics> methodStatisticsMap;

    public void msgRecive(MonitorMessage monitorMessage) {
        Long methodid = monitorMessage.getMethodId();
        if (methodStatisticsMap == null) {
            statisticsUpdate();
        }
        AbstractMethodStatistics method = methodStatisticsMap.get(methodid);
        if (method != null) {
            method.msgRecive(monitorMessage);
        }
        this.visitors++;
        update(monitorMessage);
    }

    public abstract void update(MonitorMessage monitorMessage);

    public void sumup() {
        handleResult(this);
        if (methodStatisticsMap != null) {
            for (AbstractMethodStatistics method : methodStatisticsMap.values()) {
                method.sumup();
            }
        }
        clear();

    }

    public abstract void handleResult(AbstractResourceStatistics resource);

    public void clear() {
        this.visitors = 0;
        attributeClear();
    }

    public abstract void attributeClear();

    public void statisticsUpdate() {
        if (methodStatisticsMap == null) {
            methodStatisticsMap = new ConcurrentHashMap<>();
        }
        methodStatisticsMap.clear();
        Map<Long, String> resources = toplogyInter.getMethodByResourceId(this.id);
        if (resources != null) {
            for (Map.Entry<Long, String> resourceEntry : resources.entrySet()) {
                AbstractMethodStatistics method = SpringUtil.getBean(AbstractMethodStatistics.class);
                method.setId(resourceEntry.getKey());
                method.setName(resourceEntry.getValue());
                method.setParentResource(this);
                methodStatisticsMap.put(resourceEntry.getKey(), method);
            }
        }
    }
}
