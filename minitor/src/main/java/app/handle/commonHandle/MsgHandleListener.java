package app.handle.commonHandle;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
//消息处理监听器

public interface MsgHandleListener {
    //统计结果之后
    public void afterSumup();
}
