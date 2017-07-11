package app.handle.commonHandle.warehouse;

import app.handle.commonHandle.warehouse.statistics.AbstractOverallStatistics;
import entitylib.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
@Component
public class MsgWarehouseImpl implements WarehoseInter {
    @Autowired
    AbstractOverallStatistics overallStatistics;

    @Override
    public void putMsg(MsgEntity msgEntity) {
        overallStatistics.msgRecive(msgEntity);
    }

    @Override
    public void sumup() {
        overallStatistics.sumup();
    }

    @Override
    public void statisticsUpdate() {
        overallStatistics.statisticsUpdate();
    }
}
