package app;

import app.feignClient.ServiceB;
import app.feignClient.ServiceC;
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

    @Override
    public String methodA1() {
        System.out.println("serviceA 调用 serviceB");
        try {
            Thread.currentThread().sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serviceB.serviceB1();

        return "methodA1";
    }

    @Override
    public String methodA2() {
        System.out.println("serviceA 调用 serviceC");
        try {
            Thread.currentThread().sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serviceC.serviceC2();
        return "methodA2";
    }
}
