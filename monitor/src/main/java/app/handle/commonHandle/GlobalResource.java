package app.handle.commonHandle;

import app.database.dao.OverallRepository;
import app.database.domain.Monitor_overall;
import app.util.SpringUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/11.
 */
public class GlobalResource {
//    /** 网关拓扑图  **/
//    private static List toplogy=new LinkedList();
//
//    //获得所有组 eg:1->user
//    public static Map<Long,String> getAllGroups(){
//        Map<Long,String> map = new HashMap<>();
//        map.put(1l,"group1");
//        map.put(2l,"group2");
//        return map;
//    }
//    //获得所有资源
//    public static Map<Long,String> getAllResources(){
//        Map<Long,String> map = new HashMap<>();
//        map.put(1l,"resource1");
//        map.put(2l,"resource2");
//        return map;
//    }
//    //获得所有方法
//    public static Map<Long,String> getAllMethods(){
//        Map<Long,String> map = new HashMap<>();
//        map.put(1l,"get");
//        map.put(2l,"post");
//        return map;
//    }
//
//    //获得所有服务
//    public static Map<Long,String> getAllServices(){
//        Map<Long,String> map = new HashMap<>();
//        map.put(1l,"service1");
//        map.put(2l,"service2");
//        return map;
//    }
//
//    //根据组ID获得组下所有资源
//    public static Map<Long,String> getResoucesByGroupId(long groupid){
//        if(groupid==1l){
//            Map<Long,String> map = new HashMap<>();
//            map.put(1l,"resource1");
//            map.put(2l,"resource2");
//            return map;
//        }else
//            return null;
//    }
//    //根据资源ID获得所有方法
//    public static Map<Long,String> getMethodByResourceId(long resourceid){
//        if(resourceid==1l){
//            Map<Long,String> map = new HashMap<>();
//            map.put(1l,"get");
//            map.put(2l,"post");
//            return map;
//        }else
//            return null;
//    }
//
//    //根据服务ID 获取服务名称
//    public static String getServiceNameById(Long id){
//        return null;
//    }
//
//    public static long getServiceIdBymethodId(long methodId){
//        if(methodId==1l){
//            return 1l;
//        }
//        return 2l;
//    }
//    public static void update(){
//
//    }


    /**
     * database
     */

    //当前最新添加的overall id
    private static Monitor_overall CurrentOverall;

    public static Monitor_overall getCurrentOverall(){
        if(CurrentOverall==null){
            OverallRepository overallRepository = SpringUtil.getBean(OverallRepository.class);
            CurrentOverall = overallRepository.findFirstByIdIsNotNullOrderByIdDesc();
        }
        return CurrentOverall;
    }

    public static void setCurrentOverall(Monitor_overall currentOverall) {
        CurrentOverall = currentOverall;
    }
}
