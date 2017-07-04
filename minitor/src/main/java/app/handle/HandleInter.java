package app.handle;

import entitylib.MsgEntity;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public interface HandleInter {
    /**
     * 接收消息
     * @param msgEntity
     */
    public void msgRecive(MsgEntity msgEntity);

    /**
     * 获得访问量
     * @return
     */
    public int getTotalAccess();


}
