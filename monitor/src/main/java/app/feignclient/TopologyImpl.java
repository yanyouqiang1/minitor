package app.feignclient;

import app.feignclient.entity.Group;
import app.feignclient.entity.Method;
import app.feignclient.entity.Resource;
import app.handle.commonHandle.warehouse.statistics.TopologyInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/29.
 */
@Component
public class TopologyImpl implements TopologyInter {
    @Autowired
    GatewayManager gatewayManager;

    private List<Group> groups;

    public void init(){
        groups = gatewayManager.getGroups();
    }

    @Override
    public Map<Long, String> getAllGroups() {
        if(groups==null){
            init();
        }
        Map<Long,String> map = new HashMap<>();
        for(Group g:groups){
            map.put(g.getId(),g.getGroupName());
        }
        return map;
    }

    @Override
    public Map<Long, String> getAllServices() {
        if(groups==null){
            init();
        }
        Map<Long,String> map = new HashMap<>();
        for(Group g:groups){
            List<Resource> resources = g.getResources();
            for(Resource r:resources){
                if(map.get(r.getServiceId())==null){
                    map.put(r.getService().getId(),r.getService().getServiceName());
                }
            }
        }
        return map;
    }

    @Override
    public Map<Long, String> getResourceByGroupId(long groupid) {
        if(groups==null){
            init();
        }
        Map<Long,String> map = new HashMap<>();
        for(Group g:groups){
            if(g.getId()==groupid){
                List<Resource> resources = g.getResources();
                for(Resource r:resources){
                    map.put(r.getId(),r.getResourceName());
                }
            }
        }
        return map;
    }

    @Override
    public Map<Long, String> getMethodByResourceId(long resourceid) {
        if(groups==null){
            init();
        }
        Map<Long,String> map = new HashMap<>();
        for(Group g:groups){
            List<Resource> resources = g.getResources();
            for(Resource r:resources){
               if(r.getId()==resourceid){
                   List<Method> methods = r.getMethods();
                   for(Method m:methods){
                       map.put(m.getId(),m.getMethodName());
                   }
               }
            }
        }
        return map;
    }

    @Override
    public long getServiceIdByMethodId(long methodId) {
        if(groups==null){
            init();
        }
        boolean isfind = false;
        for(Group g:groups) {
            List<Resource> resources = g.getResources();
            for (Resource r : resources) {
                List<Method> methods = r.getMethods();
                for(Method m:methods){
                    if(m.getId()==methodId){
                        isfind = true;
                    }
                }
                if(isfind){
                    return r.getServiceId();
                }
            }
        }
        return -1;
    }

    @Override
    public void update() {
        init();
    }
}
