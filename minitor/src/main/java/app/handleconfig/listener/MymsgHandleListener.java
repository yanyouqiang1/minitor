package app.handleconfig.listener;

import app.database.service.KeeptoSave;
import app.handle.commonHandle.MsgHandleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/7.
 */
@Component
public class MymsgHandleListener implements MsgHandleListener {

    @Autowired
    KeeptoSave keeptoSave;

    @Override
    public void afterSumup() {
        keeptoSave.dosave();
    }

}
