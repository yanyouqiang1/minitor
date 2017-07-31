package app.feignclient;

import app.feignclient.entity.Group;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */
@FeignClient("gateway-manager")
public interface GatewayManager {
    @RequestMapping("/groups")
    public List<Group> getGroups();
}
