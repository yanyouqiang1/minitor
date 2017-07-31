package app.handle.commonHandle.warehouse;

import entitylib.MonitorMessage;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public interface WarehoseInter {
    /**
     *  仓库加入信息
     * @param monitorMessage
     */
    public void putMsg(MonitorMessage monitorMessage);


    /**
     * 统计消息
     */
    public void sumup();


    /***
     *  服务有更新
     */
    public void statisticsUpdate();

}
