package app.handleconfig.filter;

import app.handle.commonHandle.Msgfilter;
import entitylib.ResponseMessage;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/5.
 *  状态码过滤器
 */
@Component("httpStatusFilter")
public class HttpStatusFilter implements Msgfilter {
    @Override
    public ResponseMessage filter(ResponseMessage responseMessage) {
        int status = responseMessage.getHttpStatus();
        int adjust; //调整之后
        if(status<200){
            adjust = 100;
        }else if(status<300){
            adjust = 200;
        }else if(status<400){
            adjust = 300;
        }else if (status<500){
            adjust = 400;
        }else{
            adjust = 500;
        }
        responseMessage.setHttpStatus(adjust);
        return responseMessage;
    }
}
