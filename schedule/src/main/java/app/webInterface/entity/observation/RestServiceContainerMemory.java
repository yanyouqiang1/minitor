package app.webInterface.entity.observation;

import app.schedule.rancherImpl.entity.ResultData;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
@Data
public class RestServiceContainerMemory {
    private String serviceName;
    private List<ContainerMemory> containerMemorys;

    public RestServiceContainerMemory() {
        containerMemorys = new LinkedList<ContainerMemory>();
    }

    @Data
    public static class ContainerMemory {
        private String containerName;
        private List<ContainerMemoryPoint> data;

        public ContainerMemory(String containerName) {
            this.containerName = containerName;
            data = new LinkedList<ContainerMemoryPoint>();
        }

        public void setData(ResultData resultData) {
            for(ResultData.PrometheusPoint prometheusPoint:resultData.getPoints()){
                ContainerMemoryPoint containerMemoryPoint = new ContainerMemoryPoint();
                containerMemoryPoint.setTime(prometheusPoint.getTime());
                containerMemoryPoint.setValue(prometheusPoint.getValue());
                this.data.add(containerMemoryPoint);
            }
        }
    }

    @Data
    public static class ContainerMemoryPoint {
        private double time;
        private String value;

    }
}
