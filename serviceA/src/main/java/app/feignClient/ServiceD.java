package app.feignClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/9/12.
 */
@FeignClient("ServiceD")
public interface ServiceD {
    @RequestMapping("/methodD1")
    public String serviceD1();

}
