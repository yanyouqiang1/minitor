package app.feignclient.monitor;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */
@FeignClient("monitor")
public interface Monitor {
    //获得服务下所有方法
    @RequestMapping("/schedule/getServiceMethods")
    public List<MonitorMethod> getServiceMethods(String serviceName);

    //获得服务最近访问量
    @RequestMapping("/schedule/getServiceLatestVisitor")
    public long getServiceLatestVisitor(String serviceName);

    //获得方法最近访问量
    @RequestMapping("/schedule/getMethodLatestVisitor")
    public long getMethodLatestVisitor(String methodName);

    //获得方法一段时间的响应时间
    @RequestMapping("/schedule/getMethodRecentResponseTime")
    public int[] getMethodRecentResponseTime(String methodName);
}
