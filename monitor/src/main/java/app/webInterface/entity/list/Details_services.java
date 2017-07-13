package app.webInterface.entity.list;

import app.database.domain.Monitor_method;
import app.database.domain.Monitor_services;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
public class Details_services {
    List<Monitor_method> methods;
    String name;
    Long id;
    public void generate(List<Monitor_method> monitor_methods,String serviceName,long id){
        this.methods = monitor_methods;
        this.name = serviceName;
        this.id = id;
    }

    public List<Monitor_method> getMethods() {
        return methods;
    }

    public void setMethods(List<Monitor_method> methods) {
        this.methods = methods;
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
}
