package app.handle.commonHandle;

import app.database.dao.OverallRepository;
import app.feignclient.GatewayManager;
import app.feignclient.entity.Group;
import app.handle.HandleInter;
import app.handle.commonHandle.servicehouse.ServiceHouseInter;
import app.handle.commonHandle.warehouse.WarehoseInter;
import entitylib.RequestMessage;
import entitylib.ResponseMessage;
import entitylib.ServiceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
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

    @Autowired
    ServiceHouseInter serviceHouseInter;

    @Override
    public void msgReceive(ResponseMessage responseMessage) {
        //消息过滤器
        Iterator iterable = msgfilterChain.iterator();
        while(iterable.hasNext()){
            Msgfilter msgfilter = (Msgfilter) iterable.next();
            responseMessage = msgfilter.filter(responseMessage);
        }
        warehoseInter.putResponseMsg(responseMessage);
    }

    @Override
    public void msgReceive(RequestMessage requestMessage) {
        warehoseInter.putRequestMsg(requestMessage);
    }

    @Scheduled(initialDelay = 5*1000, fixedDelay = 5*1000)
    private void handle(){
        System.out.println("统计数据:"+System.currentTimeMillis());
        //统计消息
        warehoseInter.sumup();
        //触发监听器
        if(this.msgHandleListener!=null){
            msgHandleListener.afterSumup();
        }
        serviceHouseInter.sumup();
    }

    @Override
    public void msgReceive(ServiceMessage serviceMessage) {
        serviceHouseInter.messageReceive(serviceMessage);
    }

    @Autowired
    GatewayManager gatewayManager;

    @Scheduled(fixedDelay = 60*1000)
    private void statisticUpdate(){
        System.out.println("更新网关数据:"+System.currentTimeMillis());
        warehoseInter.statisticsUpdate();
    }

    @Autowired
    OverallRepository overallRepository;

   // @Scheduled(fixedDelay = 60*60*1000)
    private void doClean(){
        System.out.println("清理数据");
        Date boforeDate = new Date(new Date().getTime()-60*60*1000);
        overallRepository.deleteAllByCreateTimeBefore(boforeDate);
    }
    public void setMsgHandleListener(MsgHandleListener msgHandleListener) {
        this.msgHandleListener = msgHandleListener;
    }

    public List<Msgfilter> getMsgfilterChain() {
        return msgfilterChain;
    }
}
