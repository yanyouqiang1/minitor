package app.rancher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/31.
 */
@Component
@Data
public class RancherPresetValue {
    private List<ServiceHook> webHooks;

    private String accesskey;
    private String secret;
    private String stackApiUrl;
    private String host,port;

    public RancherPresetValue(RancherConfig rancherConfig){
        webHooks = new LinkedList<ServiceHook>();
        Map<String,Map<String,String>> hooks = rancherConfig.getWebHooks();
        for(Map.Entry<String,Map<String,String>> entry:hooks.entrySet()){
            ServiceHook serviceHook =new ServiceHook();
            serviceHook.setServiceName(entry.getKey());
            Map<String,String> map = entry.getValue();
            serviceHook.setUpUrl(map.get("up"));
            serviceHook.setDownUrl(map.get("down"));
            webHooks.add(serviceHook);
        }

        host = rancherConfig.getHost();
        port = rancherConfig.getPort();
        accesskey = rancherConfig.getAPIkey().get("accesskey");
        secret = rancherConfig.getAPIkey().get("secret");
        stackApiUrl = "http://" + host + ":" + port + "/" + rancherConfig.getStackApiUrl();
    }
    public boolean setServiceUpUrl(String serviceName, String upUrl){
        for(ServiceHook serviceHook: webHooks){
            if(serviceHook.getServiceName().equals(serviceName)){
                serviceHook.setUpUrl(upUrl);
                System.out.println("设置:"+serviceName+",upUrl 成功！");
                return true;
            }
        }
        ServiceHook serviceHook = new ServiceHook();
        serviceHook.setServiceName(serviceName);
        serviceHook.setUpUrl(upUrl);
        webHooks.add(serviceHook);
        return true;
    }
    public boolean setServiceDownUrl(String serviceName,String downUrl){
        for(ServiceHook serviceHook: webHooks){
            if(serviceHook.getServiceName().equals(serviceName)){
                serviceHook.setDownUrl(downUrl);
                System.out.println("设置:"+serviceName+",downUrl 成功！");
                return true;
            }
        }
        ServiceHook serviceHook = new ServiceHook();
        serviceHook.setServiceName(serviceName);
        serviceHook.setDownUrl(downUrl);
        webHooks.add(serviceHook);
        return true;
    }
    public String getServiceUpUrl(String serviceName){
        for(ServiceHook serviceHook: webHooks){
            if(serviceHook.getServiceName().equals(serviceName)){
                return serviceHook.getUpUrl();
            }
        }
        System.out.println("没有找到:"+serviceName+"服务设置，是否忘记?");
        return "";
    }
    public String getServiceDownUrl(String serviceName){
        for(ServiceHook serviceHook: webHooks){
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
