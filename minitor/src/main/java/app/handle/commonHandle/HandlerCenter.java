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
public class HandlerCenter implements HandleInter {
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

    @Scheduled(initialDelay = 10000, fixedRate = 10000)
    private void handle(){
        //统计消息
        warehoseInter.sumup();
        //触发监听器
        if(this.msgHandleListener!=null){
            msgHandleListener.afterSumup();
        }
    }

    public void setMsgHandleListener(MsgHandleListener msgHandleListener) {
        this.msgHandleListener = msgHandleListener;
    }

    public List<Msgfilter> getMsgfilterChain() {
        return msgfilterChain;
    }
}
