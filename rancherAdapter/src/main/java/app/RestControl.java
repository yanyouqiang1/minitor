package app;

import app.rancher.RancherStack;
import app.innerInterface.RancherAdapter;
import app.outerInterface.RestInter;
import app.outerInterface.setting.CommonReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/9/8.
 */
@RestController
public class RestControl extends RancherAdapter{
    @Autowired
    RestInter restInter;

    @RequestMapping(value = "/setting/service/webhook/up",method = RequestMethod.POST)
    public CommonReply setWebhookUp(@RequestParam(name = "serviceName",required = true)String serviceName, @RequestParam(name = "url",required = false,defaultValue = "")String webhookUp){
        return restInter.setWebhookUp(serviceName,webhookUp);
    }
    @RequestMapping(value = "/setting/service/webhook/down",method = RequestMethod.POST)
    public CommonReply setWebhookDown(@RequestParam(name = "serviceName",required = true)String serviceName, @RequestParam(name = "url",required = false,defaultValue = "")String webhookDown){
        return restInter.setWebhookDown(serviceName,webhookDown);
    }

    @Autowired
    RancherStack rancherStack;

    @RequestMapping("/test1")
    public String test1(){
        rancherStack.upService("ServiceA");
        return "up ServiceA";
    }

    @RequestMapping("/test2")
    public String test2(){
        rancherStack.downService("ServiceA");
        return "down ServiceA";
    }
}
