package app.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class VisitorAop {
    String serviceName = "ServiceD";
    int counts = 0;

    public VisitorAop() {
    }

    @Pointcut("execution(* app.RestControl.*(..))")
    public void annotationPointCut() {

    }

    @Before("annotationPointCut()")
    public void before() {
        synchronized (VisitorAop.class) {
            counts++;
        }
    }

    @Autowired
    RabbitChannel rabbitChannel;

    @Scheduled(fixedRate = 1000l)
    public void send() {
        synchronized (VisitorAop.class) {
            ServiceMessage serviceMessage = new ServiceMessage();
            serviceMessage.setServiceName(serviceName);
            serviceMessage.setCount(counts);
            rabbitChannel.requestOutPut().send(MessageBuilder.withPayload(serviceMessage).build());
            counts = 0;
        }
    }
}
