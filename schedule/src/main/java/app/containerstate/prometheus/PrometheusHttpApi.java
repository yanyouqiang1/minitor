package app.containerstate.prometheus;

/**
 * Created by Administrator on 2017/7/28.
 */
public class PrometheusHttpApi {
    public static String urlSplicer(String address,String containerName,String start,String end,String step){
        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(address);
        sb.append("/api/v1/query_range?query=");
        sb.append("sum(rate(container_cpu_usage_seconds_total{container_label_io_rancher_container_name=\"");
        sb.append(containerName);
        sb.append("\"}[20s]))");
        sb.append("&start=");
        sb.append(start);
        sb.append("&end=");
        sb.append(end);
        sb.append("&step=");
        sb.append(step);
        return sb.toString();

    }
}
