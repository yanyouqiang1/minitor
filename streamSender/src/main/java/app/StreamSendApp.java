package app;

import entitylib.MsgEntity;
import messagechannel.RabbitChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Administrator on 2017/5/28 0028.
 */
@SpringBootApplication
@RestController
@EnableBinding(RabbitChannel.class)
public class StreamSendApp {
    public static void main(String[] args){
        SpringApplication.run(StreamSendApp.class,args);
    }

    @Autowired
    private RabbitChannel rabbitChannel;

    @RequestMapping("/ka")
    public String ka(){
        rabbitChannel.requestOutPut().send(MessageBuilder.withPayload("hello").build());
        return "ok";
    }

    static int a = 100;
    @RequestMapping("/send")
    public String send(){
        MsgEntity msgEntity = new MsgEntity();
        msgEntity.setHttpStatus(200);
        msgEntity.setGroupId(1l);
        msgEntity.setResourceId(1l);
        msgEntity.setMethodId(1l);
        msgEntity.setServiceId(1l);
        msgEntity.setResponseTime(400);
        rabbitChannel.responseOutPut().send(MessageBuilder.withPayload(msgEntity).build());
        return "ok";
    }
    static int b=200;
    @RequestMapping("/send2")
    public String send2(){
        MsgEntity msgEntity = new MsgEntity();
        msgEntity.setHttpStatus(200);
        msgEntity.setGroupId(1l);
        msgEntity.setResourceId(1l);
        msgEntity.setMethodId(1l);
        msgEntity.setServiceId(1l);
        msgEntity.setResponseTime(400);
        rabbitChannel.responseOutPut().send(MessageBuilder.withPayload(msgEntity).build());
        return "ok";
    }

    @RequestMapping("/test")
    public String test(@RequestParam(name = "group",required = true)long group,@RequestParam(name="resource",required = true) long resource,@RequestParam(name = "method",required = true)long method,@RequestParam(name = "response",required = true)int response,@RequestParam(name = "service",required = true)long service,@RequestParam(name = "httpstatus",defaultValue = "200")int httpstatus){
        MsgEntity msgEntity = new MsgEntity();
        msgEntity.setHttpStatus(httpstatus);
        msgEntity.setGroupId(group);
        msgEntity.setResourceId(resource);
        msgEntity.setMethodId(method);
        msgEntity.setServiceId(service);
        msgEntity.setResponseTime(response);
        rabbitChannel.responseOutPut().send(MessageBuilder.withPayload(msgEntity).build());
        return "ok";
    }
}
