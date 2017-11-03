package app.outerInterface;

import app.rancher.RancherPresetValue;
import app.outerInterface.setting.CommonReply;
import app.outerInterface.setting.CommonReplyBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/9/8.
 */
@Component
public class RestImpl implements RestInter {
    @Autowired
    RancherPresetValue presetValue;

    @Override
    public CommonReply setWebhookUp(String serviceName, String upUrl) {
        if(presetValue.setServiceUpUrl(serviceName,upUrl)) {
            return CommonReplyBuilder.buildSuccessReply();
        }else{
            return CommonReplyBuilder.buildErrorReply("设置消息失败");
        }
    }

    @Override
    public CommonReply setWebhookDown(String serviceName, String downUrl) {
        if(presetValue.setServiceDownUrl(serviceName,downUrl)) {
            return CommonReplyBuilder.buildSuccessReply();
        }else{
            return CommonReplyBuilder.buildErrorReply("设置消息失败");
        }
    }
}
