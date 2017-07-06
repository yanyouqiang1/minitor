package app.handleconfig.filter;

import app.handle.commonHandle.Msgfilter;
import entitylib.MsgEntity;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/5.
 * 方法名称大小写规范过滤器
 */
@Component("methodFilter")
public class MethodFilter implements Msgfilter {
    @Override
    public MsgEntity filter(MsgEntity msgEntity) {
        String method = msgEntity.getMethod();
        method = method.toLowerCase();
        msgEntity.setMethod(method);
        return msgEntity;
    }
}
