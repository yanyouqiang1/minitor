package app;

import app.handle.MsgReciveInter;
import entitylib.MsgEntity;
import app.handle.MinitorConfig;
import app.handle.MsgLinkedBlockingQueue;
import app.handle.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import app.schedule.rancherImpl.RancherConfig;
import app.schedule.rancherImpl.RancherStack;
import app.schedule.util.BidirectionalMap;

import java.util.*;

/**
 * Created by Administrator on 2017/5/28 0028.
 */
@SpringBootApplication
@EnableBinding(Sink.class)
@Controller
@EnableScheduling
@EnableConfigurationProperties({RancherConfig.class, MinitorConfig.class})
public class ReciverApp {
    public static void main(String[] args){
        SpringApplication.run(ReciverApp.class,args);
    }

    @Autowired
    MsgReciveInter msgRecive;

    @StreamListener(Sink.INPUT)
    public void handle(MsgEntity msgEntity){
        msgRecive.msgRecived(msgEntity);
    }

    @RequestMapping("/index")
    public String index(Model mv){
        return "index";
    }

    @RequestMapping("/")
    public String map(){
        return "nav";
    }


    @RequestMapping("/test")
    String test(Model mv){
        Map msgMap = new HashMap<String,MsgLinkedBlockingQueue>();
        String name = "service_1";
        MsgLinkedBlockingQueue msgQue = new MsgLinkedBlockingQueue();
        MsgEntity msgEntity = new MsgEntity("service_1",200);
        msgQue.addFirst(msgEntity);
        msgMap.put(name,msgQue);

        name = "service_2";
        MsgLinkedBlockingQueue msgQue2 = new MsgLinkedBlockingQueue();
        MsgEntity msgEntity2 = new MsgEntity("service_2",300);
        msgQue2.addFirst(msgEntity2);
        msgMap.put(name,msgQue2);
        msgMap.put(name,msgQue2);

        mv.addAttribute("msgQueue",msgMap);
//        Map msgMap = new HashMap<String,MsgLinkedBlockingQueue>();
//        int[] s = {18, 18, 18, 18, 12, 13, 10};
//        mv.addAttribute("config",s);

        return "test";
    }

    @RequestMapping(value = "/getData")
    @ResponseBody
    Object getData(){
        Map msgQue = Splitter.getMsgMap();
        return msgQue;
    }

    @Autowired
    RancherConfig rancherConfig;

    @RequestMapping("/testconfig")
    @ResponseBody
    String get2(){
        return rancherConfig.getStackapisurl();
    }

    @Autowired
    RancherStack rancherStack;

    @RequestMapping("/testrancher")
    @ResponseBody
    String testrancher(){
        rancherStack.getTopologyMap();
        return "test";
    }

    @RequestMapping("/topology")
    String topology(Model m){
        //获得服务列表
        Map map = rancherStack.getTopologyMap().getStringMaps();
        BidirectionalMap RtoS = rancherStack.getRtoS();
        String[] services = new String[map.size()];
        Iterator iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            String rancherName = iterator.next().toString();
            services[Integer.valueOf(map.get(rancherName).toString())] = RtoS.getV(rancherName).toString();
        }
        m.addAttribute("services",services);
        //服务关系
        m.addAttribute("relation",rancherStack.getTopologyMap().getRelation());
        return "topologyMap";
    }
}
