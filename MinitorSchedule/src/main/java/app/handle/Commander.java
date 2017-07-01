package app.handle;


import entitylib.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import app.schedule.ScheduleInter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
public class Commander {

    @Autowired
    ScheduleInter scheduleInter;

    MinitorConfig minitorConfig;

    public Commander(MinitorConfig minitorConfig) {
        this.minitorConfig = minitorConfig;
    }
    static final int detectintval=1000; //检测间隔

    @Scheduled(initialDelay = detectintval,fixedRate = detectintval)
    public void schedule() {
        List serviceOverList = new ArrayList<String>();
        Map msgQue = Splitter.getMsgMap();
        Iterator iterator = msgQue.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, MsgLinkedBlockingQueue> entry = (Map.Entry) iterator.next();
            String servcieName = entry.getKey();
            MsgLinkedBlockingQueue queue = entry.getValue();
            if (queue.size() < minitorConfig.minsampling) {  //少于采样数据直接跳过
                System.out.println("少于采样数据直接跳过");
                continue;
            }
            if (overLoad(queue,servcieName)) {
                serviceOverList.add(servcieName);
                System.out.println(servcieName + "负载过大");
            }
        }
        if(scheduleInter.overLoadHandle(serviceOverList)){
            scheduleInter.handleAfter(msgQue,serviceOverList);
        }
    }

    /***
     * 判断是否过载
     * @param queue 采样队列
     * @return
     */
    private boolean overLoad(MsgLinkedBlockingQueue queue,String serviceName) {
        Map serviceSet = minitorConfig.getServices().get(serviceName);
        int acceptime = Integer.valueOf(serviceSet.get("acceptime").toString());
        int limitime = Integer.valueOf(serviceSet.get("limitime").toString());
        Iterator iterator = queue.iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            MsgEntity msgEntity = (MsgEntity) iterator.next();
            if (msgEntity.getResponseTime() > acceptime) {
                counter++;
            }
            if (msgEntity.getResponseTime() >= limitime) {
                counter = MsgLinkedBlockingQueue.QueSize;
                break;
            }
        }
        if (counter > MsgLinkedBlockingQueue.QueSize / 2) {
            return true;
        } else
            return false;
    }
}
