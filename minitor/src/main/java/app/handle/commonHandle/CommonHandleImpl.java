package app.handle.commonHandle;

import app.handle.HandleInter;
import app.handle.commonHandle.warehouse.WarehoseInter;
import entitylib.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class CommonHandleImpl implements HandleInter {
    private MsgHandleListener msgHandleListener;
    private List<Msgfilter> msgfilterChain= new LinkedList<Msgfilter>();
    @Autowired
    WarehoseInter warehoseInter;

    @Override
    public void msgRecive(MsgEntity msgEntity) {
        //消息过滤器
        Iterator iterable = msgfilterChain.iterator();
        while(iterable.hasNext()){
            Msgfilter msgfilter = (Msgfilter) iterable.next();
            msgEntity = msgfilter.filter(msgEntity);
        }
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

    public List<Msgfilter> getMsgfilterChain() {
        return msgfilterChain;
    }
}
