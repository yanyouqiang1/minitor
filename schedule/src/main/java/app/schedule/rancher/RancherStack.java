package app.schedule.rancher;

import app.manage.entity.ServiceDetail;
import app.schedule.rancher.rancherUtil.BidirectionalMap;
import app.schedule.rancher.rancherUtil.JsonUtil;
import app.schedule.rancher.rancherUtil.TopologyMap;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5 0005.
 */
@Component
public class RancherStack {
    BidirectionalMap RtoS;// rancher服务别名《---》真正服务名
    TopologyMap topologyMap;//服务拓扑图

    @Autowired
    RancherOS rancherOS;


    @PostConstruct
    public void init() {
        try {
            //对应关系初始化
            RtoS = new BidirectionalMap<String,String>();
            List<String> list = rancherOS.getStackServices();

            //拓扑图初始化
            topologyMap = new TopologyMap(list);

            for(int i=0;i<list.size();i++){
                RtoS.put(list.get(i),rancherOS.getServiceName(list.get(i)));

                topologyMap.addRelation(list.get(i),rancherOS.getlinkedServices(list.get(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public BidirectionalMap getRtoS() {
        return RtoS;
    }

    public TopologyMap getTopologyMap() {
        return topologyMap;
    }

    public ServiceDetail getServiceDetial(String servcieName){
        String rancherName = RtoS.getK(servcieName).toString();
        String json = rancherOS.getJson(rancherName);

        ServiceDetail service = new ServiceDetail();
        service.setName(servcieName);
        try {
            service.setScale(Integer.valueOf(JsonUtil.getList(json, "scale").indexOf(0)));
        }catch (Exception e){
            System.out.println(e);
        }
        return service;
    }
}
