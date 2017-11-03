package app.outerInterface.entity.list;

import app.database.domain.Monitor_method;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Data
public class Details_resources {
    List<Monitor_method> methods;
    String name;
    Long id;
    public void generate(List<Monitor_method> methods,String resourceName,long id){
        this.methods = methods;
        this.name=resourceName;
        this.id = id;
    }

}
