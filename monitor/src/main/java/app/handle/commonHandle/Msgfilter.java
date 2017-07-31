package app.handle.commonHandle;

import entitylib.MonitorMessage;

/**
 * Created by Administrator on 2017/7/5.
 */
public interface Msgfilter {
    public MonitorMessage filter(MonitorMessage monitorMessage);
}
