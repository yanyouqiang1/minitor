package app.outerInterface.entity.list;

import app.database.domain.Monitor_method;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Data
public class Details_services {
    List<Monitor_method> methods;
    String name;
    Long id;
    public void generate(List<Monitor_method> monitor_methods,String serviceName,long id){
        this.methods = monitor_methods;
        this.name = serviceName;
        this.id = id;
    }

}
