package wang.jeese.springcloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * Created by jeese on 2017/5/30.
 */
public class MonitorEndFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(MonitorEndFilter.class);

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        System.out.println("monitorStartFilter");
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.put("EndTime", System.currentTimeMillis());
        return null;
    }
}
