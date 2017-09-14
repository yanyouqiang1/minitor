package app;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/9/12.
 */
public interface ServiceB {
    @RequestMapping("/methodB1")
    public String methodB1();

    @RequestMapping("/methodB2")
    public String methodB2();
}
