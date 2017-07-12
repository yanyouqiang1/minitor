package app.webInterface.entity.list;

import app.database.domain.Monitor_method;
import app.database.domain.Monitor_resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/10.
 */
public class Details_group {
    Map<Monitor_resource,List<Monitor_method>> groups;

    public Details_group() {
        this.groups = new HashMap<>();
    }

    public void addGroup(Monitor_resource resource,List<Monitor_method> methods){
        groups.put(resource, methods);
    }

    public Map<Monitor_resource, List<Monitor_method>> getGroups() {
        return groups;
    }

    public void setGroups(Map<Monitor_resource, List<Monitor_method>> groups) {
        this.groups = groups;
    }
}
