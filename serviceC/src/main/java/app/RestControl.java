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
        //费时操作 大约328ms
        for(int i=0;i<1000000000;i++){

        }
        return "methodC1";
    }

    @Override
    public String methodC2() {
        return "methodC2";
    }
}
