package app.innerInterface;

import app.innerInterface.targetAdapter.Adapter;
import app.innerInterface.targetAdapter.AdapterResultData;
import app.innerInterface.targetAdapter.AdapterService;
import app.innerInterface.targetAdapter.SimplifyService;
import app.rancher.RancherService;
import app.rancher.RancherStack;
import app.rancher.entity.ResultData;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */
public class RancherAdapter implements Adapter {

    @Autowired
    RancherStack rancherStack;

    @Override
    public List<AdapterService> getServices() {
        List<RancherService> rancherServices = rancherStack.getRancherServices();
        List<AdapterService> adapterServices = new LinkedList<AdapterService>();
        for(RancherService rancherService:rancherServices){
            adapterServices.add(AdapterService.generate(rancherService));
        }
        return adapterServices;
    }

    @Override
    public AdapterResultData getCpuRateByContainerName(String containerName) {
        ResultData resultData =  rancherStack.getContainerCpuStatusByName(containerName);
        return AdapterResultData.generate(resultData);
    }

    @Override
    public AdapterResultData getMemoryByContainerName(String containerName) {
        ResultData resultData =  rancherStack.getContainerMemoryStatusByName(containerName);
        return AdapterResultData.generate(resultData);
    }
    @Override
    public boolean upService(String serviceName) {
        return rancherStack.upService(serviceName);
    }

    @Override
    public boolean downService(String serviceName) {
        return rancherStack.downService(serviceName);
    }

    @Override
    public SimplifyService simplifyOverloadList(@RequestBody SimplifyService simplifyService) {
        List<RancherService> rancherServiceLinkedList = new LinkedList<RancherService>();
        for(AdapterService adapterService:simplifyService.getAdapterServices()){
            rancherServiceLinkedList.add(rancherStack.findService(adapterService.getName()));
        }
        //ConcurrentModificationException
        List<RancherService> deleteServiceList = new LinkedList<>();

        for(RancherService rancherService:rancherServiceLinkedList){
            List<RancherService> linkService = rancherService.getLinkedServices();
            for(RancherService service: linkService){
                if(rancherServiceLinkedList.contains(service)){
                    deleteServiceList.add(rancherService);
//                    rancherServiceLinkedList.remove(rancherService);
                    break;
                }
            }
        }
        rancherServiceLinkedList.removeAll(deleteServiceList);

        simplifyService.getAdapterServices().clear();
        for(RancherService rancherService:rancherServiceLinkedList){
            simplifyService.getAdapterServices().add(AdapterService.generate(rancherService));
        }

        return simplifyService;
    }
}
