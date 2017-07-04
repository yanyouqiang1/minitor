package app.handle.commonHandle;

import app.handle.HandleInter;
import app.handle.commonHandle.warehouse.WarehoseInter;
import entitylib.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
@Component
public class CommonHandleImpl implements HandleInter {
    @Autowired
    WarehoseInter warehoseInter;

    @Override
    public void msgRecive(MsgEntity msgEntity) {
        warehoseInter.putMsg(msgEntity);
    }

    @Override
    public int getTotalAccess() {
        return warehoseInter.getTotalAccess();
    }


}
