package app.manage;

import app.manage.entity.ServiceDetail;

import java.util.List;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
public interface ManageInter {

    //服务规模
    public boolean upService(String serviceName);
    public boolean downService(String serviceName);

    //服务详情
    public ServiceDetail getServiceDetail(String serviceName);

    //总体服务拓扑图
    public int[][] getToplogyMap();
    public List<String> getAllServcie();

}
