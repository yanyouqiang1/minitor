package app.outerInterface.entity.list;

import app.database.domain.Monitor_method;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Data
public class Details {
    List<Monitor_method> methods;
    public void generate(List<Monitor_method> monitor_methods){
        this.methods = monitor_methods;
    }

}
