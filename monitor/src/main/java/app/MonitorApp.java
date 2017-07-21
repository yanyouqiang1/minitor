package app;

import app.handle.HandleInter;
import entitylib.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
@SpringBootApplication
@EnableBinding(Sink.class)
@EnableScheduling
@RestController
public class MonitorApp {
    public static void main(String[] args){
        SpringApplication.run(MonitorApp.class,args);
    }

    @Autowired
    HandleInter handleInter;

    @StreamListener(Sink.INPUT)
    public void handle(MsgEntity msgEntity){
        handleInter.msgRecive(msgEntity);
    }

    @RequestMapping("/")
    public String string(){
        return "welcome";
    }

}
