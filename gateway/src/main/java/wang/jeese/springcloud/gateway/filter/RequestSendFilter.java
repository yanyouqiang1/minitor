package wang.jeese.springcloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import wang.jeese.springcloud.gateway.messagechannel.RabbitChannel;
import wang.jeese.springcloud.gateway.model.RequestMessage;
import wang.jeese.springcloud.gateway.service.AuthorizationService;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Created by jeese on 2017/6/1.
 */
public class RequestSendFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(RequestSendFilter.class);

    @Autowired
    private RabbitChannel rabbitChannel;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER + 3;
    }

    @Override
    public boolean shouldFilter() {
        System.out.println("requestSendFilter");
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Long groupId = (Long) ctx.get("GroupId");
        Long resourceId = (Long) ctx.get("ResourceId");
        Long methodId = (Long) ctx.get("MethodId");
        Long serviceId = (Long) ctx.get("ServiceId");
        RequestMessage requestMessage = new RequestMessage(groupId, resourceId, methodId, serviceId);
        rabbitChannel.requestOutPut().send(MessageBuilder.withPayload(requestMessage).build());
//        logger.info("version:2017-07-30-01");
        return null;
    }
}
