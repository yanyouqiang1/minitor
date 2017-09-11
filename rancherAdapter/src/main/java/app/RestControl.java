package app;

import app.webInterface.RestInter;
import app.webInterface.setting.CommonReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/9/8.
 */
@RestController
public class RestControl {
    @Autowired
    RestInter restInter;

    @RequestMapping(value = "/setting/service/{serviceName}/webhook/up",method = RequestMethod.PUT)
    public CommonReply setWebhookUp(@PathVariable(name = "serviceName",required = true)String serviceName, @RequestParam(name = "webhookUp",required = false,defaultValue = "")String webhookUp){
        return restInter.setWebhookUp(serviceName,webhookUp);
    }
    @RequestMapping(value = "/setting/service/{serviceName}/webhook/down",method = RequestMethod.PUT)
    public CommonReply setWebhookDown(@PathVariable(name = "serviceName",required = true)String serviceName, @RequestParam(name = "webhookDown",required = false,defaultValue = "")String webhookDown){
        return restInter.setWebhookDown(serviceName,webhookDown);
    }


}
