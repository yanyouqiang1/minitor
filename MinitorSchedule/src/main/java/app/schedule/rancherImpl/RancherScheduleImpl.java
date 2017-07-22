package app.schedule.rancherImpl;

import app.handle.MsgLinkedBlockingQueue;
import app.schedule.ScheduleInter;
import org.springframework.beans.factory.annotation.Autowired;
import app.schedule.util.BidirectionalMap;
import app.schedule.util.TopologyMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/3 0003.
 */
@Component
public class RancherScheduleImpl implements ScheduleInter {
    @Autowired
    RancherOS rancherOS;

    @Autowired
    RancherStack rancherStack;
    @Override
    public boolean overLoadHandle(List<String> serviceOverList) {

        if(serviceOverList.isEmpty()){
            System.out.println("无高负载服务！！");
            return true;
        }
        System.out.println("rancherImpl 处理中!"+serviceOverList);
        //寻找问题根源
        //List<String> source = findSoruce(serviceOverList);

        //  增加服务
        for (String str:serviceOverList){
            System.out.println("增加服务："+str);
//            rancherOS.scaleService(str,true);
        }

        System.out.println("rancherImpl 处理完毕！");
        return true;
    }

    /***
     * 根据拓扑寻找负载根源
     * @param serviceOverList
     * @return
     */
    private List<String> findSoruce(List<String> serviceOverList){
        BidirectionalMap bm = rancherStack.getRtoS();
        TopologyMap topologyMap = rancherStack.getTopologyMap();

        List<String> over = new ArrayList<String>(serviceOverList);
        for(int i=0;i<serviceOverList.size();i++){
            String sourcerancherName = bm.getK(serviceOverList.get(i)).toString(); //获得rancher 别名
            for(int j=i+1;j<serviceOverList.size();j++){
                String desrancherName = bm.getK(serviceOverList.get(j)).toString(); //获得rancher 别名
                if(topologyMap.judgeRelation(sourcerancherName,desrancherName)){
                    over.remove(serviceOverList.get(i)); //存在调用关系则去除
                    break;
                }
            }
        }

//        for(String source:serviceOverList){
//            String sourcerancherName = bm.getK(source).toString(); //获得rancher 别名
//            for(String des:serviceOverList){
//                String desrancherName = bm.getK(source).toString(); //获得rancher 别名
//                if(topologyMap.judgeRelation(sourcerancherName,desrancherName)){
//                    over.remove(source); //存在调用关系则去除
//                    break;
//                }
//            }
//        }
        return over;
    }

    @Override
    public void handleAfter(Map<String, MsgLinkedBlockingQueue> msgQueue, List<String> serviceOverList) {
        for(String overService:serviceOverList){
            MsgLinkedBlockingQueue msgLinkedBlockingQueue = msgQueue.get(overService);
            msgLinkedBlockingQueue.clear();
        }
    }
}
