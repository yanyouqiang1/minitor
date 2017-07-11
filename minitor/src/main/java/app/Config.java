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
public class Config {

    @Autowired @Qualifier("httpStatusFilter")
    Msgfilter httpStatusFilter;

    @Autowired @Qualifier("methodFilter")
    Msgfilter methodFilter;

    @Autowired
    MsgHandleListener msgHandleListener;
    @Bean
    public HandleInter getcommonHandle(){
        HandlerCenter commonHandle  = new HandlerCenter();
        commonHandle.getMsgfilterChain().add(httpStatusFilter);
        commonHandle.getMsgfilterChain().add(methodFilter);
        commonHandle.setMsgHandleListener(msgHandleListener);
        return commonHandle;
    }


    @Autowired
    GroupStatisticsResultHandler groupStatisticsHandler;
    @Autowired
    MethodStatisticsResultHandler methodStatisticsHandler;
    @Autowired
    OverallStatisticsResultHandler overallStatisticsHandler;

    @Bean
    @Scope("prototype")
    AbstractGroupStatistics groupStatistics(){
        AbstractGroupStatistics groupStatistics = new GroupStatistics();
        groupStatistics.setStatisticsHandler(groupStatisticsHandler);
        return groupStatistics;
    }

    @Bean
    AbstractOverallStatistics overallStatistics(){
        AbstractOverallStatistics abstractOverallStatistics = new OverallStatistics();
        abstractOverallStatistics.setOverallStatisticsHandler(overallStatisticsHandler);
        return abstractOverallStatistics;
    }

    @Bean
    @Scope("prototype")
    AbstractMethodStatistics methodStatistics(){
        AbstractMethodStatistics abstractMethodStatistics = new MethodStatistics();
        abstractMethodStatistics.setMethodStatisticsHandler(methodStatisticsHandler);
        return abstractMethodStatistics;
    }



}
