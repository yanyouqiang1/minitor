package app.handleconfig.analyisis;

import app.handle.commonHandle.warehouse.statistics.AbstractServiceStatistics;
import entitylib.MsgEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/11.
 */
@Component
@Scope("prototype")
public class ServiceStatistics extends AbstractServiceStatistics {
    @Override
    public void update(MsgEntity msgEntity) {
        
    }

    @Override
    public void attributeClear() {

    }

    @Override
    public void handleResult(AbstractServiceStatistics service) {

    }


}
