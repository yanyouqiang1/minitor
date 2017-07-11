package app.handleconfig.analyisis;

import app.handle.commonHandle.warehouse.statistics.AbstractGroupStatistics;
import entitylib.MsgEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/6.
 */
@Component
@Scope("prototype")
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
