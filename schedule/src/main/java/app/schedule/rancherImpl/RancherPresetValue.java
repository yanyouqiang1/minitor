package app.schedule.rancherImpl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
@Component
@Data
public class RancherPresetValue {
    private List<ServiceHook> webhooks;

    private String accesskey;
    private String secret;
    private String stackApiUrl;
    private String host,port;

    public RancherPresetValue(RancherConfig rancherConfig){
        webhooks = new LinkedList<ServiceHook>();
        host = rancherConfig.getHost();
        port = rancherConfig.getPort();
        accesskey = rancherConfig.getAPIkey().get("accesskey");
        secret = rancherConfig.getAPIkey().get("secret");
        stackApiUrl = "http://" + host + ":" + port + "/" + rancherConfig.getStackapisurl();
    }
    public void setServicUpUrl(String serviceName,String upUrl){
        for(ServiceHook serviceHook:webhooks){
            if(serviceHook.getServiceName().equals(serviceName)){
                serviceHook.setUpUrl(upUrl);
                System.out.println("设置:"+serviceName+",upUrl 成功！");
            }
        }
    }
    public void setServiceDownUrl(String serviceName,String downUrl){
        for(ServiceHook serviceHook:webhooks){
            if(serviceHook.getServiceName().equals(serviceName)){
                serviceHook.setDownUrl(downUrl);
                System.out.println("设置:"+serviceName+",downUrl 成功！");
            }
        }
    }
    public String getServiceUpUrl(String serviceName){
        for(ServiceHook serviceHook:webhooks){
            if(serviceHook.getServiceName().equals(serviceName)){
                return serviceHook.getUpUrl();
            }
        }
        System.out.println("没有找到:"+serviceName+"服务设置，是否忘记?");
        return "";
    }
    public String getServiceDownUrl(String serviceName){
        for(ServiceHook serviceHook:webhooks){
            if(serviceHook.getServiceName().equals(serviceName)){
                return serviceHook.getDownUrl();
            }
        }
        System.out.println("没有找到:"+serviceName+"服务设置，是否忘记?");
        return "";
    }




    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServiceHook{
        String serviceName;
        String upUrl;
        String downUrl;
    }
}
