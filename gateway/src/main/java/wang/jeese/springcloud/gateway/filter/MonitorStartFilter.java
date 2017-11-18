package wang.jeese.springcloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import wang.jeese.springcloud.gateway.messagechannel.RabbitChannel;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * Created by jeese on 2017/5/30.
 */
public class MonitorStartFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(MonitorStartFilter.class);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
//        System.out.println("monitorEndFilter");
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.put("StartTime", System.currentTimeMillis());
        return null;
    }
}
