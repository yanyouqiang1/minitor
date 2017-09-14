package app;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/9/12.
 */
public interface ServiceA {
    @RequestMapping("/methodA1")
    public String methodA1();

    @RequestMapping("/methodA2")
    public String methodA2();
}
