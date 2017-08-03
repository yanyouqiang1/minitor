package app.handle.commonHandle.warehouse;

import app.handle.commonHandle.warehouse.statistics.AbstractOverallStatistics;
import entitylib.RequestMessage;
import entitylib.ResponseMessage;
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
    public void putResponseMsg(ResponseMessage responseMessage) {
        overallStatistics.msgReceive(responseMessage);
    }

    @Override
    public void putRequestMsg(RequestMessage requestMessage) {
        overallStatistics.msgReceive(requestMessage);
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
