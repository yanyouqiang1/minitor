package app.schedule;

import app.handle.MsgLinkedBlockingQueue;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/3 0003.
 */
public interface ScheduleInter {
    /***
     * 负载过大时候的处理
     * @return 是否处理成功
     */
    boolean overLoadHandle(List<String> serviceOverList);

    /***
     * 负载处理过后
     */
    void handleAfter(Map<String, MsgLinkedBlockingQueue> msgQueue,List<String> serviceOverList);

}
