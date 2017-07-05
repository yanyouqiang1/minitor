package app;

import app.handle.HandleInter;
import app.handle.commonHandle.CommonHandleImpl;
import app.handle.commonHandle.Msgfilter;
import app.handle.commonHandle.warehouse.HistoryDataListener;
import app.handle.commonHandle.warehouse.MsgWarehouseImpl;
import app.handle.commonHandle.warehouse.WarehoseInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
@Configuration
public class Config {
    @Autowired
    HistoryDataListener historyDataListener;

    @Bean
    public WarehoseInter getWarehose(){
        MsgWarehouseImpl msgWarehouse = new MsgWarehouseImpl();
        msgWarehouse.setHistoryDataListener(historyDataListener);
        return msgWarehouse;
    }

    @Autowired @Qualifier("httpStatusFilter")
    Msgfilter httpStatusFilter;

    @Autowired @Qualifier("methodFilter")
    Msgfilter methodFilter;

    @Bean
    public HandleInter getcommonHandle(){
        CommonHandleImpl commonHandle  = new CommonHandleImpl();
        commonHandle.getMsgfilterChain().add(httpStatusFilter);
        commonHandle.getMsgfilterChain().add(methodFilter);
        return commonHandle;
    }

}
