package app.feignclient.monitor;

import app.Schedule.AbstractMethod;
import app.Schedule.implementation.Method;
import lombok.Data;

/**
 * Created by Administrator on 2017/9/8.
 */
@Data
public class MonitorMethod {
    private String name;

    public static AbstractMethod change(MonitorMethod monitorMethod) {
        AbstractMethod abstractMethod = new Method();
        abstractMethod.setName(monitorMethod.getName());
        return abstractMethod;
    }
}
