package app.schedule;

import app.schedule.util.BidirectionalMap;
import app.schedule.util.TopologyMap;
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
            System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIII");
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
}
