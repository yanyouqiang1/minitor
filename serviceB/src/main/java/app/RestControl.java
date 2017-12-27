package app;

import app.feignClient.ServiceC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/9/12.
 */
@RestController
public class RestControl implements ServiceB {
    @Autowired
    ServiceC serviceC;

    @Autowired
    ServiceConfig config;
    @Override
    public String methodB1() {
        System.out.println("servcieB 调用 serviceC");
        try {
            Thread.currentThread().sleep(config.method1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0;i<config.times1;i++){
            serviceC.serviceC1();
        }
        return "methodB1";
    }

    @Override
    public String methodB2() {
        try {
            Thread.currentThread().sleep(config.method2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "methodB2";
    }
}
