package app.webInterface.entity.list;

import app.database.domain.Monitor_method;
import app.database.domain.Monitor_resource;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
public class Details_group {
    List<Monitor_method> methods;
    String name;
    Long id;

    public void generate(List<Monitor_method> monitor_methods, String groupName, long id) {
        this.methods = monitor_methods;
        this.name = groupName;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Monitor_method> getMethods() {
        return methods;
    }

    public void setMethods(List<Monitor_method> methods) {
        this.methods = methods;
    }
}
