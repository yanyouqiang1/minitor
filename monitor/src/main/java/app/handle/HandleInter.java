package app.handle;

import entitylib.RequestMessage;
import entitylib.ResponseMessage;
import entitylib.ServiceMessage;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public interface HandleInter {
    /**
     * 接收消息
     * @param responseMessage
     */
    public void msgReceive(ResponseMessage responseMessage);

    public void msgReceive(RequestMessage requestMessage);

    public void msgReceive(ServiceMessage serviceMessage);
}
