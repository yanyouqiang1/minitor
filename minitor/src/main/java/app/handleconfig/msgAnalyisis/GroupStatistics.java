package app.handleconfig.msgAnalyisis;

import app.handle.commonHandle.warehouse.AbstractGroupStatistics;
import entitylib.MsgEntity;

/**
 * Created by Administrator on 2017/7/6.
 */
public class GroupStatistics extends AbstractGroupStatistics {
    @Override
    public void update(MsgEntity msgEntity) {
        this.visitors++;
    }
}
