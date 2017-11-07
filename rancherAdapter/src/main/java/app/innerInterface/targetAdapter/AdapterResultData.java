package app.innerInterface.targetAdapter;

import app.rancher.entity.ResultData;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/29.
 */
@Data
public class AdapterResultData {
    private String status;
    private List<Point> points;

    public AdapterResultData() {
        this.points = new LinkedList<Point>();
    }

    public static class Point{
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
        public static Point generate(ResultData.PrometheusPoint prometheusPoint){
            Point point = new Point();
            point.setTime(prometheusPoint.getTime());
            point.setValue(prometheusPoint.getValue());
            return point;
        }
    }

    public static AdapterResultData generate(ResultData resultData){
        AdapterResultData adapterResultData = new AdapterResultData();
        adapterResultData.setStatus(resultData.getStatus());
        List<ResultData.PrometheusPoint> prometheusPoints = resultData.getPoints();
        for(ResultData.PrometheusPoint prometheusPoint:prometheusPoints){
            adapterResultData.getPoints().add(Point.generate(prometheusPoint));
        }
        return  adapterResultData;
    }
}

