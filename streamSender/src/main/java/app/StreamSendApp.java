package app;

import entitylib.MsgEntity;
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
@EnableBinding(Source.class)
public class StreamSendApp {
    public static void main(String[] args){
        SpringApplication.run(StreamSendApp.class,args);
    }

    private Source source;

    @Autowired
    public StreamSendApp(Source source){
        this.source = source;
    }

    @RequestMapping("/ka")
    public String ka(){
        source.output().send(MessageBuilder.withPayload("hello").build());
        return "ok";
    }

    static int a = 100;
    @RequestMapping("/send")
    public String send(){
        MsgEntity msgEntity = new MsgEntity();
        msgEntity.setHttpStatus(200);
        msgEntity.setGroupid(1l);
        msgEntity.setResouceid(1l);
        msgEntity.setMethodid(1l);
        msgEntity.setServiceid(1l);
        msgEntity.setResposneTime(400);
        source.output().send(MessageBuilder.withPayload(msgEntity).build());
        return "ok";
    }
    static int b=200;
    @RequestMapping("/send2")
    public String send2(){
        MsgEntity msgEntity = new MsgEntity();
        msgEntity.setHttpStatus(200);
        msgEntity.setGroupid(1l);
        msgEntity.setResouceid(1l);
        msgEntity.setMethodid(2l);
        msgEntity.setServiceid(1l);
        msgEntity.setResposneTime(400);
        source.output().send(MessageBuilder.withPayload(msgEntity).build());
        return "ok";
    }

    @RequestMapping("/test")
    public String test(@RequestParam(name = "group",required = true)long group,@RequestParam(name="resource",required = true) long resource,@RequestParam(name = "method",required = true)long method,@RequestParam(name = "response",required = true)int response,@RequestParam(name = "service",required = true)long service,@RequestParam(name = "httpstatus",defaultValue = "200")int httpstatus){
        MsgEntity msgEntity = new MsgEntity();
        msgEntity.setHttpStatus(httpstatus);
        msgEntity.setGroupid(group);
        msgEntity.setResouceid(resource);
        msgEntity.setMethodid(method);
        msgEntity.setServiceid(service);
        msgEntity.setResposneTime(response);
        source.output().send(MessageBuilder.withPayload(msgEntity).build());
        return "ok";
    }
}
