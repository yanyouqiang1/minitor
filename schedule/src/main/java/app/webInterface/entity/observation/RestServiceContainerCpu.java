package app.webInterface.entity.observation;

import app.schedule.rancherImpl.entity.ResultData;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
@Data
public class RestServiceContainerCpu {
    private String serviceName;
    private List<ContainerCPU> containerCpus;

    public RestServiceContainerCpu() {
        containerCpus = new LinkedList<ContainerCPU>();
    }

    @Data
    public static class ContainerCPU {
        private String containerName;
        private List<ContainerCpuPoint> data;

        public ContainerCPU(String containerName) {
            this.containerName = containerName;
            data = new LinkedList<ContainerCpuPoint>();
        }

        public void setData(ResultData resultData) {
            for(ResultData.PrometheusPoint prometheusPoint:resultData.getPoints()){
                ContainerCpuPoint containerCpuPoint = new ContainerCpuPoint();
                containerCpuPoint.setTime(prometheusPoint.getTime());
                containerCpuPoint.setValue(prometheusPoint.getValue());
                this.data.add(containerCpuPoint);
            }
        }
    }

    @Data
    public static class ContainerCpuPoint {
        private double time;
        private String value;

    }
}
