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
        for(int i=0;i<3;i++){
            System.out.println("servcieB 调用 serviceC");
            serviceC.serviceC1();
        }
        //费时操作 大约38 ms
        for(int i=0;i<100000000;i++){

        }
        return "methodB1";
    }

    @Override
    public String methodB2() {
        return "methodB2";
    }
}
