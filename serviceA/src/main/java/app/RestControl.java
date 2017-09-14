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
        for(int i=0;i<3;i++){
            System.out.println("serviceA 调用 serviceB");
            serviceB.serviceB1();
        }

        return "methodA1";
    }

    @Override
    public String methodA2() {
        for(int i=0;i<3;i++){
            System.out.println("serviceA 调用 serviceC");
            serviceC.serviceC2();
        }
        return "methodA2";
    }
}
