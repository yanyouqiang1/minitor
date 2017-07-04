package app;

import app.handle.commonHandle.warehouse.HistoryDataListener;
import app.handle.commonHandle.warehouse.MsgWarehouseImpl;
import app.handle.commonHandle.warehouse.WarehoseInter;
import org.springframework.beans.factory.annotation.Autowired;
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
}
