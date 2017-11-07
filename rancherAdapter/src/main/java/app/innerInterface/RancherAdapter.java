package app.innerInterface;

import app.innerInterface.targetAdapter.Adapter;
import app.innerInterface.targetAdapter.AdapterResultData;
import app.innerInterface.targetAdapter.AdapterService;
import app.rancher.RancherService;
import app.rancher.RancherStack;
import app.rancher.entity.ResultData;
import org.springframework.beans.factory.annotation.Autowired;

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
    public LinkedList<AdapterService> simplifyOverloadList(LinkedList<AdapterService> serviceList) {
        List<RancherService> rancherServiceLinkedList = new LinkedList<RancherService>();
        for(AdapterService adapterService:serviceList){
            rancherServiceLinkedList.add(rancherStack.findService(adapterService.getName()));
        }
        for(RancherService rancherService:rancherServiceLinkedList){
            List<RancherService> linkService = rancherService.getLinkedServices();
            for(RancherService service: linkService){
                if(rancherServiceLinkedList.contains(service)){
                    rancherServiceLinkedList.remove(rancherService);
                    break;
                }
            }
        }

        serviceList.clear();
        for(RancherService rancherService:rancherServiceLinkedList){
            serviceList.add(AdapterService.generate(rancherService));
        }

        return serviceList;
    }
}
