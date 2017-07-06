package app;

import app.handle.HandleInter;
import app.handle.commonHandle.HandlerCenter;
import app.handle.commonHandle.Msgfilter;
import app.handle.commonHandle.warehouse.*;
import app.handleconfig.msgAnalyisis.GroupStatistics;
import app.handleconfig.msgAnalyisis.MethodStatistics;
import app.handleconfig.msgAnalyisis.OverallStatistics;
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

    @Bean
    public HandleInter getcommonHandle(){
        HandlerCenter commonHandle  = new HandlerCenter();
        commonHandle.getMsgfilterChain().add(httpStatusFilter);
        commonHandle.getMsgfilterChain().add(methodFilter);
        return commonHandle;
    }


    @Autowired
    GroupStatisticsHandler groupStatisticsHandler;
    @Autowired
    MethodStatisticsHandler methodStatisticsHandler;
    @Autowired
    OverallStatisticsHandler overallStatisticsHandler;

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
