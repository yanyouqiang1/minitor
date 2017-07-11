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
     * 统计消息
     */
    public void sumup();


    /***
     *  服务有更新
     */
    public void statisticsUpdate();
}
