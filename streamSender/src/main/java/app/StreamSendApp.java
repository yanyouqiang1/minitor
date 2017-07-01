package app;

import entitylib.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
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

    static int a = 100;
    @RequestMapping("/send")
    public String send(){
        MsgEntity msgEntity = new MsgEntity("client",a);
        a+=100;
        source.output().send(MessageBuilder.withPayload(msgEntity).build());
        return a+"";
    }
    static int b=200;
    @RequestMapping("/send2")
    public String send2(){
        MsgEntity msgEntity = new MsgEntity("service ",b);
        b+=100;
        source.output().send(MessageBuilder.withPayload(msgEntity).build());
        return a+"";
    }

    @RequestMapping("/random")
    public String random(){
        int b=150;
        b+=Math.random()*100;
        MsgEntity msgEntity = new MsgEntity("service2",b);
        source.output().send(MessageBuilder.withPayload(msgEntity).build());
        return b+"";
    }

}
