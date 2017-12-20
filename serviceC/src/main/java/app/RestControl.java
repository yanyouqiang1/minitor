package app;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/9/12.
 */
@RestController
public class RestControl implements ServiceC {
    @Autowired
    ServiceConfig config;
    @Override
    public String methodC1() {
        try {
            Thread.currentThread().sleep(config.method1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "methodC1";
    }

    @Override
    public String methodC2() {
        try {
            Thread.currentThread().sleep(config.method2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "methodC2";
    }
}
