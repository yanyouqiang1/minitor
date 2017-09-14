package app.feignClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/9/12.
 */
@FeignClient("serviceC")
public interface ServiceC {
    @RequestMapping("/methodC1")
    public String serviceC1();
    
}
