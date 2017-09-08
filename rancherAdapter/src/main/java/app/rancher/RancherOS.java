package app.rancher;

import app.globalUtil.HttpRequest;
import app.rancher.util.JsonUtil;
import app.rancher.util.LocalWebCookies;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
@Component
public class RancherOS {

    @Autowired
    private RancherPresetValue rancherPresetValue;

    private String rancherServiceURL = "";
    private String rancherContainerURL = "";

    //获取服务列表
    public List<String> getStackServices() throws JSONException {
        String result = HttpRequest.sendGetwithAuthen(rancherPresetValue.getAccesskey(), rancherPresetValue.getSecret(), rancherPresetValue.getStackApiUrl(), "");
        return JsonUtil.getList(result, "serviceIds");
    }

    public String getServiceName(String serviceId) throws JSONException {
        return queryRancherServiceResult(serviceId,"name").get(0);
    }

    public int getServiceScale(String serviceId) throws JSONException {
        return Integer.valueOf(queryRancherServiceResult(serviceId,"scale").get(0));
    }

    public List<String> getLinkedServices(String serviceId) throws JSONException {
        return queryRancherServiceResult(serviceId,"linkedServices");
    }

    public List<String> getServiceContainerNames(String serviceId) throws JSONException {
        List<String> containerNames = new LinkedList<String>();
        //获得instanceIds
        List<String> instanceIds = queryRancherServiceResult(serviceId,"instanceIds");
        for (String instanceId : instanceIds) {
            String containerName = queryRancherContainerResult(instanceId,"name").get(0);
            containerNames.add(containerName);
        }
        return containerNames;
    }

    //增加服务，或者减少服务
    public boolean scaleService(String serviceName, boolean upOrdown) {
        String webhooksURL = "";
        if (upOrdown) {
            webhooksURL = rancherPresetValue.getServiceUpUrl(serviceName);
        } else {
            webhooksURL = rancherPresetValue.getServiceDownUrl(serviceName);
        }
        if (webhooksURL.equals("")) {
            System.out.println("scale" + serviceName + "容器失败");
            return false;
        }
        HttpRequest.httpClientPost(webhooksURL, null, "UTF8");
        return true;
    }

    private String getRancherServiceURL() {
        if (rancherServiceURL == "") {
            //获得rancher 服务地址
            String[] strings = rancherPresetValue.getStackApiUrl().split("projects");
            StringBuilder stringBuilder = new StringBuilder(strings[0]);
            stringBuilder.append("projects/");
            String[] strings1 = strings[1].split("/");
            stringBuilder.append(strings1[1]);
            stringBuilder.append("/services/");
            rancherServiceURL = stringBuilder.toString();
        }
        return rancherServiceURL;
    }

    private String getRancherContainerURL() {
        if (rancherContainerURL == "") {
            String[] strings = rancherPresetValue.getStackApiUrl().split("projects");
            StringBuilder stringBuilder = new StringBuilder(strings[0]);
            stringBuilder.append("projects/");
            String[] strings1 = strings[1].split("/");
            stringBuilder.append(strings1[1]);
            stringBuilder.append("/containers/");
            rancherContainerURL = stringBuilder.toString();
        }
        return rancherContainerURL;
    }
    //查询某个服务的某个属性
    private List<String> queryRancherServiceResult(String serviceId, String queryName)throws JSONException {
        String realPath = getRancherServiceURL() + serviceId;
        String restApiContent = LocalWebCookies.getApiContent(rancherPresetValue.getAccesskey(), rancherPresetValue.getSecret(), realPath, "");
        return JsonUtil.getList(restApiContent, queryName);
    }
    private List<String> queryRancherContainerResult(String containerId, String queryName)throws JSONException {
        String realPath = getRancherContainerURL() + containerId;
        String restApiContent = LocalWebCookies.getApiContent(rancherPresetValue.getAccesskey(), rancherPresetValue.getSecret(), realPath, "");
        return JsonUtil.getList(restApiContent, queryName);
    }
}
