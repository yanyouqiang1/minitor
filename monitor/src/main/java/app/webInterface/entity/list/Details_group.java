package app.webInterface.entity.list;

import app.database.domain.Monitor_method;
import app.database.domain.Monitor_resource;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Data
public class Details_group {
    List<Monitor_method> methods;
    String name;
    Long id;

    public void generate(List<Monitor_method> monitor_methods, String groupName, long id) {
        this.methods = monitor_methods;
        this.name = groupName;
        this.id = id;
    }

}
