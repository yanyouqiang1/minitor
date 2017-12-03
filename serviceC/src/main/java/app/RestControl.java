package app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/9/12.
 */
@RestController
public class RestControl implements ServiceC {
    @Override
    public String methodC1() {
        try {
            Thread.currentThread().sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "methodC1";
    }

    @Override
    public String methodC2() {
        try {
            Thread.currentThread().sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "methodC2";
    }
}
