package app;

import app.handle.HandleInter;
import app.handle.commonHandle.HandlerCenter;
import app.handle.commonHandle.MsgHandleListener;
import app.handle.commonHandle.Msgfilter;
import app.handle.commonHandle.warehouse.statistics.*;
import app.handleconfig.analyisis.GroupStatistics;
import app.handleconfig.analyisis.MethodStatistics;
import app.handleconfig.analyisis.OverallStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
@Configuration
public class MonitorConfig {

//    @Autowired @Qualifier("httpStatusFilter")
//    Msgfilter httpStatusFilter;
//
//    @Autowired
//    MsgHandleListener msgHandleListener;
//
//    @Bean
//    public HandleInter getcommonHandle(){
//        HandlerCenter commonHandle  = new HandlerCenter();
//        commonHandle.getMsgfilterChain().add(httpStatusFilter);
//        commonHandle.setMsgHandleListener(msgHandleListener);
//        return commonHandle;
//    }
//





}
