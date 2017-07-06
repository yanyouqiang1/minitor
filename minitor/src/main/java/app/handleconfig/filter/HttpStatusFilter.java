package app.handleconfig.filter;

import app.handle.commonHandle.Msgfilter;
import entitylib.MsgEntity;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/5.
 *  状态码过滤器
 */
@Component("httpStatusFilter")
public class HttpStatusFilter implements Msgfilter {
    @Override
    public MsgEntity filter(MsgEntity msgEntity) {
        int status = msgEntity.getHttpStatus();
        int adjust; //调整之后
        if(status<300){
            adjust = 200;
        }else if(status<400){
            adjust = 300;
        }else if (status<500){
            adjust = 400;
        }else{
            adjust = 500;
        }
        msgEntity.setHttpStatus(adjust);
        return msgEntity;
    }
}
