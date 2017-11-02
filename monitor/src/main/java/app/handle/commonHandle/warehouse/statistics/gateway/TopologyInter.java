package app.handle.commonHandle.warehouse.statistics.gateway;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/29.
 */
public interface TopologyInter {

    //获得所有组 eg:1->user
    Map<Long, String> getAllGroups();

    //获得所有服务
    public Map<Long, String> getAllServices();

    //根据组ID获得组下所有资源
    public List<HandleResource> getResourceByGroupId(long groupid);

    //根据资源ID获得所有方法
    public Map<Long,String> getMethodByResourceId(long resourceid);

    //通过方法id获得服务详情
    public long getServiceIdByMethodId(long methodId);

    //更新了
    public void update();

    //
    public Long getServiceIDByServiceName(String serviceName);

    public List<String> getMethodsByServiceName(String serviceName);
}
