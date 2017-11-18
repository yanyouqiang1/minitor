package wang.jeese.springcloud.gateway.fallback;

import com.netflix.discovery.converters.Auto;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import wang.jeese.springcloud.gateway.filter.ErrorFilter;
import wang.jeese.springcloud.gateway.messagechannel.RabbitChannel;
import wang.jeese.springcloud.gateway.model.ResponseMessage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ServiceFallback implements ZuulFallbackProvider {
    @Override
    public String getRoute() {
        return "*";
    }

    private static Logger logger = LoggerFactory.getLogger(ServiceFallback.class);
    @Autowired
    private RabbitChannel rabbitChannel;

    @Override
    public ClientHttpResponse fallbackResponse() {
//        throw new RuntimeException("hystrix fallback");
//        RequestContext ctx = RequestContext.getCurrentContext();
//        Long groupId = (Long) ctx.get("GroupId");
//        Long resourceId = (Long) ctx.get("ResourceId");
//        Long methodId = (Long) ctx.get("MethodId");
//        Long serviceId = (Long) ctx.get("ServiceId");
//        Long startTime = (Long) ctx.get("StartTime");
//        Long endTime = (Long) ctx.get("EndTime");
//        Integer httpStatus = 500;
//        Integer responseTime = Math.toIntExact(endTime - startTime);
//        ResponseMessage responseMessage = new ResponseMessage(groupId, resourceId, methodId, serviceId, httpStatus, responseTime);
//        rabbitChannel.responseOutPut().send(MessageBuilder.withPayload(responseMessage).build());
//        logger.info("groupId: " + groupId);
//        logger.info("resourceId: " + resourceId);
//        logger.info("methodId: " + methodId);
//        logger.info("serviceId: " + serviceId);
//        logger.info("httpStatus: " + httpStatus);
//        logger.info("responseTime: " + responseTime + " ms");
        System.out.println("serviceFall back");
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

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("hystrix fallback".getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }
}
