package app.schedule;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2017/6/6 0006.
 */
@ConfigurationProperties(prefix = "rancher")
public class RancherConfig {


    private String stackapisurl,host,port;

    private Map<String, String> APIkey = new HashMap<String, String>();

    private Map<String, Map<String, String>> webhooks = new HashMap<String, Map<String, String>>();

    public String getStackapisurl() {
        return stackapisurl;
    }

    public void setStackapisurl(String stackapisurl) {
        this.stackapisurl = stackapisurl;
    }

    public Map<String, String> getAPIkey() {
        return APIkey;
    }

    public void setAPIkey(Map<String, String> APIkey) {
        this.APIkey = APIkey;
    }

    public Map<String, Map<String, String>> getWebhooks() {
        return webhooks;
    }

    public void setWebhooks(Map<String, Map<String, String>> webhooks) {
        this.webhooks = webhooks;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

}
