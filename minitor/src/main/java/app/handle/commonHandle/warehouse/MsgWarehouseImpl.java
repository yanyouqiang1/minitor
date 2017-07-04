package app.handle.commonHandle.warehouse;

import app.handle.commonHandle.warehouse.msgstore.Statistics;
import entitylib.MsgEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
@Component
public class MsgWarehouseImpl implements WarehoseInter {
    private Statistics statistics = new Statistics();
    private Map msgMap = new HashMap<String,LinkedBlockingDeque>();

    @Override
    public void putMsg(MsgEntity msgEntity) {
        //消息储存
        LinkedBlockingDeque queue = (LinkedBlockingDeque) msgMap.get(msgEntity.getSeviceName());
        if(queue==null){
            queue = new LinkedBlockingDeque();
            msgMap.put(msgEntity.getSeviceName(),queue);
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
        LinkedBlockingDeque queue = (LinkedBlockingDeque) msgMap.get(serviceName);
        return queue.size();
    }
}
