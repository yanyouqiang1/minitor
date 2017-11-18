package wang.jeese.springcloud.gateway.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.util.StringUtils;
import wang.jeese.springcloud.gateway.service.LocateRoutesService;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jeese on 2017/5/23.
 */
public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    private static Logger logger = LoggerFactory.getLogger(CustomRouteLocator.class);
    private ZuulProperties properties;

    @Autowired
    private LocateRoutesService locateRoutesService;

    private int refreshLock = 0;

    public CustomRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
        logger.info("servletPath:{}",servletPath);
    }

    @Override
    public void refresh() {
        //一分钟刷新一次，因为erueka的原因，每30s会触发两次刷新，通过计数达到1分钟刷新一次
        if(refreshLock == 0){
            doRefresh();
            refreshLock++;
        }else{
            refreshLock++;
            if(refreshLock == 4){
                refreshLock = 0;
            }
        }
    }

    @Override
    protected Map<String, ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulRoute> routesMap = new LinkedHashMap<String, ZuulRoute>();
        //从application.properties中加载路由信息
        routesMap.putAll(super.locateRoutes());
        //从db中加载路由信息
        routesMap.putAll(locateRoutesService.locateRoutesFromDB());
        //优化一下配置
        LinkedHashMap<String, ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            // Prepend with slash if not already present.
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        return values;
    }

}
