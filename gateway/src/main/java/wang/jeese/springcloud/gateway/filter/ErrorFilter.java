package wang.jeese.springcloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import wang.jeese.springcloud.gateway.messagechannel.RabbitChannel;
import wang.jeese.springcloud.gateway.model.ResponseMessage;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;

@Component
public class ErrorFilter extends ZuulFilter {
    @Autowired
    private RabbitChannel rabbitChannel;

    private static Logger logger = LoggerFactory.getLogger(ErrorFilter.class);
    @Override
    public String filterType() {
        return ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        System.out.println("error error error error error error error error error error error error error error error");
        RequestContext ctx = RequestContext.getCurrentContext();
        Long groupId = (Long) ctx.get("GroupId");
        Long resourceId = (Long) ctx.get("ResourceId");
        Long methodId = (Long) ctx.get("MethodId");
        Long serviceId = (Long) ctx.get("ServiceId");
//        Long startTime = (Long) ctx.get("StartTime");
//        Long endTime = (Long) ctx.get("EndTime");
        Integer httpStatus = 500;
//        Integer responseTime = Math.toIntExact(endTime - startTime);
        Integer responseTime = -1;
        ResponseMessage responseMessage = new ResponseMessage(groupId, resourceId, methodId, serviceId, httpStatus, responseTime);
        rabbitChannel.responseOutPut().send(MessageBuilder.withPayload(responseMessage).build());
        logger.info("groupId: " + groupId);
        logger.info("resourceId: " + resourceId);
        logger.info("methodId: " + methodId);
        logger.info("serviceId: " + serviceId);
        logger.info("httpStatus: " + httpStatus);
        logger.info("responseTime: " + responseTime + " ms");
        return null;
    }
}
