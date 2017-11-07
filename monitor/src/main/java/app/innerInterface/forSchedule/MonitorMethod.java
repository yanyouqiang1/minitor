package app.innerInterface.forSchedule;

import app.database.domain.Monitor_method;
import lombok.Data;

/**
 * Created by Administrator on 2017/9/8.
 */
@Data
public class MonitorMethod {
    private String name;

    public static MonitorMethod generate(Monitor_method monitor_method){
        MonitorMethod monitorMethod = new MonitorMethod();
        monitorMethod.setName(monitor_method.getName());
        return monitorMethod;
    }
}
