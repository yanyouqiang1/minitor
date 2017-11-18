package wang.jeese.springcloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import wang.jeese.springcloud.gateway.messagechannel.RabbitChannel;
import wang.jeese.springcloud.gateway.model.ResponseMessage;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

/**
 * Created by jeese on 2017/5/30.
 */
public class ResponseSendFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(ResponseSendFilter.class);

    @Autowired
    private RabbitChannel rabbitChannel;

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
//        System.out.println("ResponseFilter");
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Long groupId = (Long) ctx.get("GroupId");
        Long resourceId = (Long) ctx.get("ResourceId");
        Long methodId = (Long) ctx.get("MethodId");
        Long serviceId = (Long) ctx.get("ServiceId");
        Long startTime = (Long) ctx.get("StartTime");
        Long endTime = (Long) ctx.get("EndTime");
        Integer httpStatus = (Integer)ctx.get("HttpCode");
        if(httpStatus==null){
            httpStatus = ctx.getResponseStatusCode();
        }
        Integer responseTime = Math.toIntExact(endTime - startTime);
        ResponseMessage responseMessage = new ResponseMessage(groupId, resourceId, methodId, serviceId, httpStatus, responseTime);
        rabbitChannel.responseOutPut().send(MessageBuilder.withPayload(responseMessage).build());
//        logger.info("groupId: " + groupId);
//        logger.info("resourceId: " + resourceId);
//        logger.info("methodId: " + methodId);
//        logger.info("serviceId: " + serviceId);
//        logger.info("httpStatus: " + httpStatus);
//        logger.info("responseTime: " + responseTime + " ms");
        return null;
    }
}
