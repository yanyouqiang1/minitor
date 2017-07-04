package app.handle.commonHandle.warehouse;

import entitylib.MsgEntity;

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
}
