package app.handle.commonHandle.warehouse.statistics;

import entitylib.MsgEntity;

/**
 * Created by Administrator on 2017/7/11.
 */
public interface Statistics {
    /***
     *  接收消息
     * @param msgEntity
     */
    public void msgRecive(MsgEntity msgEntity);

    /***
     *  统计信号
     */
    public void sumup();

    /***
     *  归零信号
     */
    public void clear();

    /***
     * 更新信号
     */
    public void statisticsUpdate();

}
