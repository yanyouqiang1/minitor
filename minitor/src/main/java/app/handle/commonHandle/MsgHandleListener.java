package app.handle.commonHandle;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
//消息处理监听器

public interface MsgHandleListener {
    /**
     *  在消息销毁前的处理
     * @param servcieName 服务名
     * @param msgQueue 消息体
     */
    public void msgBeforeDestroy(String servcieName,Queue msgQueue);
}
