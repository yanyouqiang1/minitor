package app;

import app.handle.HandleInter;
import app.messagechannel.RabbitChannel;
import entitylib.MonitorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
@SpringBootApplication
@EnableBinding(RabbitChannel.class)
@EnableScheduling
@RestController
@EnableDiscoveryClient
@EnableFeignClients
public class MonitorApp {
    public static void main(String[] args){
        SpringApplication.run(MonitorApp.class,args);
    }

    @Autowired
    HandleInter handleInter;

    @StreamListener(RabbitChannel.INPUT_REQUEST)
    public void handleRequest(){
        System.out.println("收到request请求");
    }

    @StreamListener(RabbitChannel.INPUT_RESPONSE)
    public void handle(MonitorMessage monitorMessage){
        System.out.println("收到response请求");
        handleInter.msgRecive(monitorMessage);
    }

    @RequestMapping("/")
    public String string(){
        return "welcome";
    }

}
