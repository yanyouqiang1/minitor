package app.schedule.rancherImpl;

import app.globalUtil.HttpRequest;
import app.schedule.rancherImpl.util.JsonUtil;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
@Component
public class RancherOS {

    @Autowired
    RancherPresetValue rancherPresetValue;

    /***
     * 获取服务列表
     * @return 服务列表
     * @throws JSONException
     */
    public List<String> getStackServices() throws JSONException {
        String result = HttpRequest.sendGetwithAuthen(rancherPresetValue.getAccesskey(), rancherPresetValue.getSecret(), rancherPresetValue.getStackApiUrl(), "");
        return JsonUtil.getList(result, "serviceIds");
    }

    /***
     * 获取服务名
     * @param serviceId
     * @return
     * @throws JSONException
     */
    public String getServiceName(String serviceId) throws JSONException {
        if (RancherServiceURL.equals("")) {
            getServiceURL();
        }
        String realpath = RancherServiceURL + serviceId;

        String result = HttpRequest.sendGetwithAuthen(rancherPresetValue.getAccesskey(), rancherPresetValue.getSecret(), realpath, "");

        return JsonUtil.getList(result, "name").get(0);
    }

    public List<String> getlinkedServices(String serviceId) throws JSONException {
        if (RancherServiceURL.equals("")) {
            getServiceURL();
        }
        String realpath = RancherServiceURL + serviceId;
        String result = HttpRequest.sendGetwithAuthen(rancherPresetValue.getAccesskey(), rancherPresetValue.getSecret(), realpath, "");
        return JsonUtil.getList(result, "linkedServices");
    }
    public int getServiceScale(String serviceId) throws JSONException {
        if (RancherServiceURL.equals("")) {
            getServiceURL();
        }
        String realpath = RancherServiceURL + serviceId;

        String result = HttpRequest.sendGetwithAuthen(rancherPresetValue.getAccesskey(), rancherPresetValue.getSecret(), realpath, "");

        return Integer.valueOf(JsonUtil.getList(result, "scale").get(0));
    }

    /***
     *  增加服务，或者减少服务
     * @param serviceName 服务名
     * @param upOrdown true为UP，false为DOWN
     * @return
     */
    public boolean scaleService(String serviceName, boolean upOrdown) {
        String webhooksURL = "";
        if (upOrdown) {
            webhooksURL = rancherPresetValue.getServiceUpUrl(serviceName);
        } else {
            webhooksURL =  rancherPresetValue.getServiceDownUrl(serviceName);
        }
        if (webhooksURL.equals("")) {
            System.out.println("scale"+serviceName+"容器失败");
            return false;
        }
        HttpRequest.httpClientPost(webhooksURL, null, "UTF8");
        return true;
    }

    String RancherServiceURL = "";

    private void getServiceURL() {
        //获得rancher 服务地址
        String[] strings = rancherPresetValue.getStackApiUrl().split("projects");
        StringBuilder stringBuilder = new StringBuilder(strings[0]);
        stringBuilder.append("projects/");

        String[] strings1 = strings[1].split("/");

        stringBuilder.append(strings1[1]);

        stringBuilder.append("/services/");

        RancherServiceURL = stringBuilder.toString();
    }

    public String getJson(String rancherName) {
        if (RancherServiceURL.equals("")) {
            getServiceURL();
        }
        String realpath = RancherServiceURL + rancherName;
        String result = HttpRequest.sendGetwithAuthen(rancherPresetValue.getAccesskey(), rancherPresetValue.getSecret(), realpath, "");
        return result;
    }
}
