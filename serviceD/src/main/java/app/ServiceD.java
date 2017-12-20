package app;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/9/12.
 */
public interface ServiceD {
    @RequestMapping("/methodD1")
    public String methodD1();

    @RequestMapping("/methodD2")
    public String methodD2();
}
