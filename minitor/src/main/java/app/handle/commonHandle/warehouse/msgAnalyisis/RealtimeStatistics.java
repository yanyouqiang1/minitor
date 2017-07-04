package app.handle.commonHandle.warehouse.msgAnalyisis;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class RealtimeStatistics {
    //服务名 -- 单个统计
   private Map<String,Singlestatistics> realtimeStatistics = new HashMap<String, Singlestatistics>();

    /**
     * 获得当前统计详情
     * @param serviceName
     * @return
     */
   public Singlestatistics getServiceStatistics(String serviceName){
       return realtimeStatistics.get(serviceName);
   }

    /**
     * 设置当前状态
     * @param serviceName
     * @param singlestatistics
     */
   public void setServcieStatics(String serviceName,Singlestatistics singlestatistics){
       realtimeStatistics.put(serviceName,singlestatistics);
   }


}
