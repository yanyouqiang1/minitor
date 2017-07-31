package app.handle;

import entitylib.MonitorMessage;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public interface HandleInter {
    /**
     * 接收消息
     * @param monitorMessage
     */
    public void msgRecive(MonitorMessage monitorMessage);

}
