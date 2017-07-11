package app.handleconfig.analyisis;

import app.handle.commonHandle.warehouse.statistics.AbstractGroupStatistics;
import entitylib.MsgEntity;

/**
 * Created by Administrator on 2017/7/6.
 */
public class GroupStatistics extends AbstractGroupStatistics {
    protected float rate;

    @Override
    public void update(MsgEntity msgEntity) {

    }
    @Override
    public void attributeClear() {
        rate =0;
    }
    @Override
    public void handleResult(AbstractGroupStatistics group) {

    }


}
