package app.feignclient;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/29.
 */
@Data
public class AbstractionResultData {
    private String status;
    private List<Point> points;

    public AbstractionResultData() {
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
    }
}

