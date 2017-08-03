package app.handle.commonHandle.warehouse;

import entitylib.RequestMessage;
import entitylib.ResponseMessage;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public interface WarehoseInter {
    /**
     *  仓库加入信息
     * @param responseMessage
     */
    public void putResponseMsg(ResponseMessage responseMessage);

    public void putRequestMsg(RequestMessage requestMessage);
    /**
     * 统计消息
     */
    public void sumup();


    /***
     *  服务有更新
     */
    public void statisticsUpdate();

}
