package app.handle;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
@ConfigurationProperties(prefix = "minitor.app.schedule")
public class MinitorConfig {
    int minsampling;
    Map<String,Map<String,String>> services = new HashMap<String, Map<String, String>>();
    public int getMinsampling() {
        return minsampling;
    }

    public void setMinsampling(int minsampling) {
        this.minsampling = minsampling;
    }

    public Map<String, Map<String, String>> getServices() {
        return services;
    }

    public void setServices(Map<String, Map<String, String>> services) {
        this.services = services;
    }
}
