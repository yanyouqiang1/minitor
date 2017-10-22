//package app.feignclient;
//
//import app.feignclient.targetAdapter.Adapter;
//import app.feignclient.targetAdapter.AdapterResultData;
//import app.feignclient.targetAdapter.AdapterService;
//import lombok.Data;
//import org.springframework.stereotype.Component;
//
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * Created by Administrator on 2017/9/19.
// */
//@Component
//public class TestAdapter implements Adapter{
//    @Override
//    public List<AdapterService> getServices() {
//        List<AdapterService> adapterServiceList = new LinkedList<>();
//        AdapterService adapterService = new AdapterService();
//        adapterService.setName("serviceA");
//        adapterService.setScale(1);
//        adapterService.setContainerNames(new LinkedList<>());
//        adapterServiceList.add(adapterService);
//
//        adapterService = new AdapterService();
//        adapterService.setName("serviceB");
//        adapterService.setScale(2);
//        adapterService.setContainerNames(new LinkedList<>());
//        adapterServiceList.add(adapterService);
//
//        adapterService = new AdapterService();
//        adapterService.setName("serviceC");
//        adapterService.setScale(1);
//        adapterService.setContainerNames(new LinkedList<>());
//        adapterServiceList.add(adapterService);
//        return adapterServiceList;
//    }
//
//    @Override
//    public AdapterResultData getCpuRateByContainerName(String containerName) {
//        return null;
//    }
//
//    @Override
//    public AdapterResultData getMemoryByContainerName(String containerName) {
//        return null;
//    }
//
//    @Override
//    public List<AdapterService> simplifyOverloadList(List<AdapterService> serviceList) {
//        return serviceList;
//    }
//
//    @Override
//    public boolean upService(String serviceName) {
//        return false;
//    }
//
//    @Override
//    public boolean downService(String serviceName) {
//        return false;
//    }
//}
