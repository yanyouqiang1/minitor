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

/**
 * Created by Administrator on 2017/7/4 0004.
 */
@SpringBootApplication
@EnableBinding(Sink.class)
@EnableScheduling
public class MinitorApp {
    public static void main(String[] args){
        SpringApplication.run(MinitorApp.class,args);
    }

    @Autowired
    HandleInter handleInter;

    @StreamListener(Sink.INPUT)
    public void handle(MsgEntity msgEntity){
        handleInter.msgRecive(msgEntity);
    }

}
