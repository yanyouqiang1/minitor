package app.handle.commonHandle;

import app.handle.HandleInter;
import app.handle.commonHandle.warehouse.WarehoseInter;
import entitylib.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
@Component
public class CommonHandleImpl implements HandleInter {
    private MsgHandleListener msgHandleListener;
    @Autowired
    WarehoseInter warehoseInter;

    @Override
    public void msgRecive(MsgEntity msgEntity) {
        warehoseInter.putMsg(msgEntity);
    }

    @Override
    public int getTotalAccess() {
        return warehoseInter.getTotalAccess();
    }

    @Scheduled(initialDelay = 10000, fixedRate = 10000)
    private void handle(){
        //处理消息
        warehoseInter.sumup();
        //调用消息销毁前的监听器
        if(this.msgHandleListener!=null){
            List<String> services = warehoseInter.getServices();
            for (String service:services){
                msgHandleListener.msgBeforeDestroy(service,warehoseInter.getServcieQueue(service));
            }
        }
        warehoseInter.destroyMsg();
    }

    public void setMsgHandleListener(MsgHandleListener msgHandleListener) {
        this.msgHandleListener = msgHandleListener;
    }
}
