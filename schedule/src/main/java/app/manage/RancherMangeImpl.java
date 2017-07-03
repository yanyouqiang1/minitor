package app.manage;

import app.manage.entity.ServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
@Component
public class RancherMangeImpl implements ManageInter {
    @Autowired
    RancherStack rancherStack;

    @Autowired
    RancherOS rancherOS;

    @Override
    public boolean upService(String serviceName) {
        return rancherOS.scaleService(serviceName,true);
    }

    @Override
    public boolean downService(String serviceName) {
        return rancherOS.scaleService(serviceName,false);
    }

    @Override
    public ServiceDetail getServiceDetail(String serviceName) {
        return rancherStack.getServiceDetial(serviceName);
    }

    @Override
    public int[][] getToplogyMap() {
        return rancherStack.getTopologyMap().getRelation();
    }

    @Override
    public List<String> getAllServcie() {
        return rancherStack.getRtoS().getListK2();
    }
}
