package app;

import app.database.domain.Monitor_overall;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/11.
 */
public class Global {
    //获得所有组 eg:1->user
    public static Map<Long,String> getGroups(){
        Map<Long,String> map = new HashMap<>();
        map.put(1l,"group1");
        map.put(2l,"group2");
        return map;
    }
    //根据组ID获得组下所有资源
    public static Map<Long,String> getResoucesByGroupId(long groupid){
        if(groupid==1l){
            Map<Long,String> map = new HashMap<>();
            map.put(1l,"resource1");
            map.put(2l,"resource2");
            return map;
        }else
            return null;
    }
    //根据资源ID获得所有方法
    public static Map<Long,String> getMethodByResourceId(long resourceid){
        if(resourceid==1l){
            Map<Long,String> map = new HashMap<>();
            map.put(1l,"get");
            map.put(2l,"post");
            return map;
        }else
            return null;
    }

    //根据服务ID 获取服务名称
    public static String getServiceNameById(Long id){
        return null;
    }

    //获得所有服务
    public static Map<Long,String> getServices(){
        Map<Long,String> map = new HashMap<>();
        map.put(1l,"service1");
        map.put(2l,"service2");
        return map;
    }

    public static void update(){

    }
    /**
     * database
     */
    //当前最新添加的overall id
    public static Monitor_overall CurrentOverall;

}
