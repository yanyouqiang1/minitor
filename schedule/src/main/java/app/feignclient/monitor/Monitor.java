package app.feignclient.monitor;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */
@FeignClient("monitor")
public interface Monitor {
    //获得服务下所有方法
    @RequestMapping("/schedule/getServiceMethods")
    public List<MonitorMethod> getServiceMethods(@RequestParam(name = "serviceName") String serviceName);

    //获得服务最近访问量
    @RequestMapping("/schedule/getServiceLatestVisitor")
    public long getServiceLatestVisitor(@RequestParam(name = "serviceName") String serviceName);

    //获得方法最近访问量
    @RequestMapping("/schedule/getMethodLatestVisitor")
    public long getMethodLatestVisitor(@RequestParam(name = "serviceName") String serviceName, @RequestParam(name = "methodName")String methodName);

    //获得方法一段时间的响应时间
    @RequestMapping("/schedule/getMethodRecentResponseTime")
    public int[] getMethodRecentResponseTime(@RequestParam(name = "serviceName") String serviceName, @RequestParam(name = "methodName")String methodName);

    @RequestMapping("/schedule/getServiceRecentResponseTime")
    public int[] getServiceRecentResponseTime(@RequestParam(name = "serviceName") String serviceName);
}
