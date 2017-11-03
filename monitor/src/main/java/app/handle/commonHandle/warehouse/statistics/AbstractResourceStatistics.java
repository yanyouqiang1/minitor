package app.handle.commonHandle.warehouse.statistics;

import app.handle.commonHandle.warehouse.statistics.gateway.TopologyInter;
import app.util.SpringUtil;
import entitylib.RequestMessage;
import entitylib.ResponseMessage;
import lombok.Data;
import lombok.Getter;
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
    @Getter
    protected long response_visitors,request_visitors;
    //自身属性
    protected Long id;
    protected String name;
    protected Long serviceId;

    //映射属性
    protected AbstractGroupStatistics parentGroup;
    Map<Long, AbstractMethodStatistics> methodStatisticsMap;

    public void msgReceive(ResponseMessage responseMessage) {
        Long methodid = responseMessage.getMethodId();
        if (methodStatisticsMap == null) {
            statisticsUpdate();
        }
        AbstractMethodStatistics method = methodStatisticsMap.get(methodid);
        if (method != null) {
            method.msgReceive(responseMessage);
        }
        this.response_visitors++;
        update(responseMessage);
    }

    @Override
    public void msgReceive(RequestMessage requestMessage) {
        Long methodid = requestMessage.getMethodId();
        if (methodStatisticsMap == null) {
            statisticsUpdate();
        }
        AbstractMethodStatistics method = methodStatisticsMap.get(methodid);
        if (method != null) {
            method.msgReceive(requestMessage);
        }
        this.request_visitors++;
    }

    public abstract void update(ResponseMessage responseMessage);

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
        this.response_visitors = 0l;
        this.request_visitors = 0l;
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
                method.setName(this.name+"/"+resourceEntry.getValue());
                method.setParentResource(this);
                methodStatisticsMap.put(resourceEntry.getKey(), method);
            }
        }
    }
}
