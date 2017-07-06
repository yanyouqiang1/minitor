package app.handleconfig.msgAnalyisis;

import app.handle.commonHandle.warehouse.AbstractMethodStatistics;
import entitylib.MsgEntity;

/**
 * Created by Administrator on 2017/7/6.
 */
public class MethodStatistics extends AbstractMethodStatistics {
    @Override
    public void update(MsgEntity msgEntity) {
        this.visitors++;
    }
}
