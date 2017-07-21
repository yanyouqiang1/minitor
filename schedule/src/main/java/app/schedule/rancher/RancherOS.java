package app.schedule.rancher;

import app.schedule.rancher.rancherUtil.HttpRequest;
import app.schedule.rancher.rancherUtil.JsonUtil;
import org.json.JSONException;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
public class RancherOS {

    String accesskey;
    String secret;
    String stackapisurl;
    String host, port;
    Map<String, Map<String, String>> webhooks;

    public RancherOS(RancherConfig rancherConfig) {
        host = rancherConfig.getHost();
        port = rancherConfig.getPort();
        accesskey = rancherConfig.getAPIkey().get("accesskey");
        secret = rancherConfig.getAPIkey().get("secret");
        stackapisurl = "http://" + host + ":" + port + "/" + rancherConfig.getStackapisurl();
        webhooks = rancherConfig.getWebhooks();
    }

    /***
     * 获取服务列表
     * @return 服务列表
     * @throws JSONException
     */
    public List<String> getStackServices() throws JSONException {
        String result = HttpRequest.sendGetwithAuthen(accesskey, secret, stackapisurl, "");
        return JsonUtil.getList(result, "serviceIds");
    }

    /***
     * 获取服务名
     * @param rancherName
     * @return
     * @throws JSONException
     */
    public String getServiceName(String rancherName) throws JSONException {
        if (RancherServiceURL.equals("")) {
            getServiceURL();
        }
        String realpath = RancherServiceURL + rancherName;

        String result = HttpRequest.sendGetwithAuthen(accesskey, secret, realpath, "");

        return JsonUtil.getList(result, "name").get(0);
    }

    public List<String> getlinkedServices(String rancherName) throws JSONException {
        if (RancherServiceURL.equals("")) {
            getServiceURL();
        }
        String realpath = RancherServiceURL + rancherName;
        String result = HttpRequest.sendGetwithAuthen(accesskey, secret, realpath, "");


        return JsonUtil.getList(result, "linkedServices");
    }

    /***
     *  增加服务，或者减少服务
     * @param serviceName 服务名
     * @param upOrdown true为UP，false为DOWN
     * @return
     */
    public boolean scaleService(String serviceName, boolean upOrdown) {
        String webhooksURL = "";
        Map serviceMap = webhooks.get(serviceName);
        if (upOrdown) {
            webhooksURL = serviceMap.get("up").toString();
        } else {
            webhooksURL = serviceMap.get("down").toString();
        }

        if (webhooksURL.equals("")) {
            return false;
        }
        HttpRequest.httpClientPost(webhooksURL, null, "UTF8");
        return true;
    }

    String RancherServiceURL = "";

    private void getServiceURL() {
        //获得rancher 服务地址
        String[] strings = stackapisurl.split("projects");
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
        String result = HttpRequest.sendGetwithAuthen(accesskey, secret, realpath, "");
        return result;
    }
}
