package wang.jeese.springcloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import wang.jeese.springcloud.gateway.service.AuthenticationService;
import wang.jeese.springcloud.gateway.service.AuthorizationService;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PROXY_KEY;

/**
 * Created by jeese on 2017/6/1.
 */
public class AuthorizationFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER + 2;
    }

    @Override
    public boolean shouldFilter() {
        System.out.println("AuthorizationFilter");
        RequestContext ctx = RequestContext.getCurrentContext();
        return authorizationService.shouldFilter(ctx);
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        authorizationService.authorize(ctx);
        return null;
    }
}
