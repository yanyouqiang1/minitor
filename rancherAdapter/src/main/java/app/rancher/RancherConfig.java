package app.rancher;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2017/6/6 0006.
 */
@ConfigurationProperties(prefix = "rancher")
@Data
public class RancherConfig {
    private String stackApiUrl;
    private String host;
    private String port;
    private Map<String, String> APIkey = new HashMap<String, String>();
    private Map<String,Map<String,String>> webHooks = new HashMap<String, Map<String, String>>();
}
