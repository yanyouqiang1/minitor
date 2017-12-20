package app;

import app.feignClient.ServiceC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/9/12.
 */
@RestController
public class RestControl implements ServiceD {
    @Autowired
    ServiceC serviceC;

    @Autowired
    ServiceConfig config;


    @Override
    public String methodD1() {
        System.out.println("serviceD 调用 serviceC");
        try {
            Thread.currentThread().sleep(config.method1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serviceC.serviceC1();

        return "methodA1";
    }

    @Override
    public String methodD2() {
//        System.out.println("serviceA 调用 serviceC");
//        try {
//            Thread.currentThread().sleep(config.method2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        serviceC.serviceC2();
        return "methodD2";
    }
}
