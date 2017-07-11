package app.handleconfig.analyisis;

import app.handle.commonHandle.warehouse.statistics.AbstractResourceStatistics;
import entitylib.MsgEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/11.
 */
@Component
@Scope("prototype")
public class ResourceStatistics extends AbstractResourceStatistics {
    @Override
    public void update(MsgEntity msgEntity) {

    }
    @Override
    public void attributeClear() {

    }
    @Override
    public void handleResult(AbstractResourceStatistics resource) {

    }


}
