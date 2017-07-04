package app.handle.commonHandle.warehouse;

import entitylib.MsgEntity;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public interface WarehoseInter {
    /**
     *  仓库加入信息
     * @param msgEntity
     */
    public void putMsg(MsgEntity msgEntity);

    /**
     * 获得总访问量
     */
    public int getTotalAccess();

    /**
     * 获得服务访问量
     * @param serviceName
     * @return
     */
    public int getServiceAccess(String serviceName);

    /**
     * 获得所有服务
     * @return
     */
    public List getServices();

    /**
     * 获得服务消息存储队列
     * @return
     */
    public Queue getServcieQueue(String serviceName);

    /**
     * 销毁消息
     */
    public void destroyMsg();

    /**
     * 统计消息
     */
    public void sumup();
}
