package app.handleconfig.msgAnalyisis;

import app.handle.commonHandle.warehouse.AbstractOverallStatistics;
import entitylib.MsgEntity;

/**
 * Created by Administrator on 2017/7/6.
 */
public class OverallStatistics extends AbstractOverallStatistics{
    @Override
    public void update(MsgEntity msgEntity) {
        this.visitors ++;

    }
}
