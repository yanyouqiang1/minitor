package app.webInterface;

import app.webInterface.setting.CommonReply;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/9/8.
 */
@Component
public class RestImpl implements RestInter {
    @Override
    public CommonReply setWebhookUp(String serviceName, String upUrl) {
        return null;
    }

    @Override
    public CommonReply setWebhookDown(String serviceName, String downUrl) {
        return null;
    }
}
