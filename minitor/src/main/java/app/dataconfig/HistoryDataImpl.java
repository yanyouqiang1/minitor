package app.dataconfig;

import app.handle.commonHandle.warehouse.HistoryDataListener;
import app.handle.commonHandle.warehouse.msgAnalyisis.Singlestatistics;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

//对于历史数据的处理，保存到MySQL 或者redis
@Component
public class HistoryDataImpl implements HistoryDataListener {
    @Override
    public void handle(String servcieName, Singlestatistics statistics) {
        System.out.println(servcieName+"历史数据处理中！");
    }
}
