package app;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/11.
 */
public class Global {
    //获得所有组 eg:1->user
    public static Map<Long,String> getGroups(){
        return null;
    }
    //根据组ID获得组下所有资源
    public static Map<Long,String> getResoucesByGroupId(long groupid){
        return null;
    }
    //根据资源ID获得所有方法
    public static Map<Long,String> getMethodByResourceId(long resourceid){
        return null;
    }

    //根据服务ID 获取服务名称
    public static String getServiceNameById(Long id){
        return null;
    }

    //获得所有服务
    public static Map<Long,String> getServices(){
        return null;
    }

    public static void update(){

    }


    //当前最新添加的overall id
    private static long currentOverallId=1;

    public static long getCurrentOverallId() {
        return currentOverallId;
    }

    public static void setCurrentOverallId(long currentOverallId) {
        Global.currentOverallId = currentOverallId;
    }
}
