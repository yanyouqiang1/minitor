package app.feignClient;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/9/12.
 */
@FeignClient("ServiceB")
public interface ServiceB {
    @RequestMapping("/methodB1")
    public String serviceB1();

}
