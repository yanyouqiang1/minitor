package app;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/9/12.
 */
public interface ServiceC {
    @RequestMapping("/methodC1")
    public String methodC1();

    @RequestMapping("/methodC2")
    public String methodC2();
}
