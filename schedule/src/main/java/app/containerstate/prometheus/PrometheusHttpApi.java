package app.containerstate.prometheus;

import app.global.HttpRequest;

/**
 * Created by Administrator on 2017/7/28.
 */
public class PrometheusHttpApi {
    public static String urlSplicer(String address,String containerName,String start,String end,String step){
        String url = "http://"+address+"/api/v1/query_range";
        StringBuilder param = new StringBuilder();
        param.append("query=");
        param.append("sum(rate(container_cpu_usage_seconds_total{container_label_io_rancher_container_name=\"");
        param.append(containerName);
        param.append("\"}[20s]))");
        param.append("&start=");
        param.append(start);
        param.append("&end=");
        param.append(end);
        param.append("&step=");
        param.append(step);
        
        return HttpRequest.sendGet(url,param.toString());

    }
}
