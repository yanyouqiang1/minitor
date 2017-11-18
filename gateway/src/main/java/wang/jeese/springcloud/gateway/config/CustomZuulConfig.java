package wang.jeese.springcloud.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wang.jeese.springcloud.gateway.filter.*;
import wang.jeese.springcloud.gateway.zuul.CustomRouteLocator;

/**
 * Created by jeese on 2017/5/24.
 */
@Configuration
public class CustomZuulConfig {

    @Autowired
    private ZuulProperties zuulProperties;
    @Autowired
    private ServerProperties server;

    @Bean
    public CustomRouteLocator routeLocator() {
        return new CustomRouteLocator(this.server.getServletPrefix(), this.zuulProperties);
    }

    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

    @Bean
    public AuthorizationFilter permissionFilter() {
        return new AuthorizationFilter();
    }

    @Bean
    public MonitorStartFilter monitorStartFilter() {
        return new MonitorStartFilter();
    }

    @Bean
    public MonitorEndFilter monitorEndFilter() {
        return new MonitorEndFilter();
    }

    @Bean
    public RequestSendFilter requestSendFilter() {
        return new RequestSendFilter();
    }

    @Bean
    public ResponseSendFilter responseSendFilter() {
        return new ResponseSendFilter();
    }

}
