package app;

import app.handle.HandleInter;
import app.messagechannel.RabbitChannel;
import entitylib.RequestMessage;
import entitylib.ResponseMessage;
import entitylib.ServiceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
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
    public void handleRequest(RequestMessage requestMessage){
        handleInter.msgReceive(requestMessage);
    }

    @StreamListener(RabbitChannel.INPUT_RESPONSE)
    public void handle(ResponseMessage responseMessage){
        handleInter.msgReceive(responseMessage);
    }

    @StreamListener(RabbitChannel.INPUT__SERVICE)
    public void handle(ServiceMessage serviceMessage){
        handleInter.msgReceive(serviceMessage);
    }

    @RequestMapping("/")
    public String string(){
        return "welcome";
    }

}
