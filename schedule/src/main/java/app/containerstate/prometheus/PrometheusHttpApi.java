package app.containerstate.prometheus;

import app.globalUtil.HttpRequest;
import app.schedule.rancherImpl.entity.ResultData;

/**
 * Created by Administrator on 2017/7/28.
 */
public class PrometheusHttpApi {
    public static ResultData getCpuData(String address, String containerName, String start, String end, String step) throws Exception {
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

        String json =  HttpRequest.sendGet(url,param.toString());
        return PrometheusJsonParser.parser(json);
    }

    public static ResultData getMemoryData(String address, String containerName, String start, String end, String step) throws Exception {
        String url = "http://"+address+"/api/v1/query_range";
        StringBuilder param = new StringBuilder();
        param.append("query=");
        param.append("sum(container_memory_rss{container_label_io_rancher_container_name=\"");
        param.append(containerName);
        param.append("\"})");
        param.append("&start=");
        param.append(start);
        param.append("&end=");
        param.append(end);
        param.append("&step=");
        param.append(step);

        String json =  HttpRequest.sendGet(url,param.toString());
        return PrometheusJsonParser.parser(json);
    }

}
