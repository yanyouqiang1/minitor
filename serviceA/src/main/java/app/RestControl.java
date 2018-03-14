package app;

import app.aspect.RabbitChannel;
import app.feignClient.ServiceB;
import app.feignClient.ServiceC;
import app.feignClient.ServiceD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/9/12.
 */
@RestController
public class RestControl implements ServiceA {
    @Autowired
    ServiceB serviceB;

    @Autowired
    ServiceC serviceC;

    @Autowired
    ServiceD serviceD;

    @Autowired
    ServiceConfig config;

    @Override
    public String methodA1() {
        System.out.println("serviceA 调用 serviceB/D/D");
        try {
            Thread.currentThread().sleep(config.method1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serviceB.serviceB1();
        serviceD.serviceD1();
        serviceD.serviceD1();
        return "methodA1";
    }

    @Override
    public String methodA2() {
        System.out.println("serviceA 调用 serviceC");
        try {
            Thread.currentThread().sleep(config.method2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serviceC.serviceC2();
        return "methodA2";
    }
}
