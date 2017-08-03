package app.schedule.rancherImpl.entity;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/29.
 */
@Data
public class ResultData {
    String status;
    List<PrometheusPoint> points;

    public ResultData() {
        this.points = new LinkedList<PrometheusPoint>();
    }

    public static class PrometheusPoint{
        Double time;
        String value;

        public Double getTime() {
            return time;
        }

        public void setTime(Double time) {
            this.time = time;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

