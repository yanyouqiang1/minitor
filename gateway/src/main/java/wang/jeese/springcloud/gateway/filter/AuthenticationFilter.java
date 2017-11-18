package wang.jeese.springcloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import wang.jeese.springcloud.gateway.service.AuthenticationService;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * Created by jeese on 2017/5/30.
 */
public class AuthenticationFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        System.out.println("AuthenticationFilter");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
        return authenticationService.shouldFilter(ctx);
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        authenticationService.authenticate(ctx);
        return null;
    }
}
