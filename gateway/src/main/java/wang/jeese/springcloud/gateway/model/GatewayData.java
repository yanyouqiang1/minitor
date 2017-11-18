package wang.jeese.springcloud.gateway.model;

import java.util.Map;

/**
 * Created by jeese on 2017/6/1.
 */
public class GatewayData {

    private static final GatewayData instance = new GatewayData();

    private Map<String, Resource> resourceMap;
    private Map<Long, String> authorityMap;

    private GatewayData() {
    }

    public static GatewayData getInstance(){
        return instance;
    }

    public Map<String, Resource> getResourceMap() {
        return resourceMap;
    }

    public void setResourceMap(Map<String, Resource> resourceMap) {
        this.resourceMap = resourceMap;
    }

    public Map<Long, String> getAuthorityMap() {
        return authorityMap;
    }

    public void setAuthorityMap(Map<Long, String> authorityMap) {
        this.authorityMap = authorityMap;
    }
}
