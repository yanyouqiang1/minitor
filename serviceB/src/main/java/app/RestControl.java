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

    @Override
    public String methodB1() {
        System.out.println("servcieB 调用 serviceC");
        try {
            Thread.currentThread().sleep(38);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serviceC.serviceC1();
        return "methodB1";
    }

    @Override
    public String methodB2() {
        try {
            Thread.currentThread().sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "methodB2";
    }
}
