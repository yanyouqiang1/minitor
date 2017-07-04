package app.handle.commonHandle.warehouse;

import app.handle.commonHandle.warehouse.msgAnalyisis.AbstractRealtimeStatistics;
import app.handle.commonHandle.warehouse.msgAnalyisis.RealtimeStatistics;
import app.handle.commonHandle.warehouse.msgAnalyisis.Singlestatistics;
import app.handle.commonHandle.warehouse.msgAnalyisis.Statistics;
import entitylib.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class MsgWarehouseImpl implements WarehoseInter {
    private Statistics statistics = new Statistics(); //总统计
    private Map msgMap = new HashMap<String,LinkedBlockingDeque>();
    private List<String> services = new ArrayList();

    @Autowired
    AbstractRealtimeStatistics realtimeStatistics; //实时统计

    //监听器
    private HistoryDataListener historyDataListener;

    @Override
    public void putMsg(MsgEntity msgEntity) {
        //消息储存
        LinkedBlockingDeque queue;
        if(!services.contains(msgEntity.getSeviceName())){
            services.add(msgEntity.getSeviceName());
            queue = new LinkedBlockingDeque();
            msgMap.put(msgEntity.getSeviceName(),queue);
        }else{
            queue = (LinkedBlockingDeque) msgMap.get(msgEntity.getSeviceName());
        }
        queue.addFirst(msgEntity);

        //更新统计量
        statistics.setTotalAcess(statistics.getTotalAcess()+1);
    }

    @Override
    public int getTotalAccess() {
        return statistics.getTotalAcess();
    }

    @Override
    public int getServiceAccess(String serviceName) {
        Singlestatistics singlestatistics = realtimeStatistics.getServiceStatistics(serviceName);
        return singlestatistics.getAccessCount();
    }
    @Override
    public List<String> getServices() {
        return services;
    }
    @Override
    public Queue getServcieQueue(String serviceName){
        return (Queue) msgMap.get(serviceName);
    }

    @Override
    public void destroyMsg() {
        services.clear();
        msgMap.clear();
        statistics.clear();
    }

    @Override
    public void sumup() {
        //历史数据处理
        if(historyDataListener!=null){
            for(String service:services){
                Singlestatistics singlestatistics = realtimeStatistics.getServiceStatistics(service);
                if(singlestatistics!=null){
                    historyDataListener.handle(service,singlestatistics);
                }
            }
        }
        //对单个服务的统计
        for(String servcie:services){
            realtimeStatistics.setServcieStatics(servcie,realtimeStatistics.fillAlgorithm((Queue) msgMap.get(servcie)));
        }

    }

    public void setHistoryDataListener(HistoryDataListener historyDataListener) {
        this.historyDataListener = historyDataListener;
    }
}
