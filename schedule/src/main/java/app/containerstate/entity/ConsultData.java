package app.containerstate.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */
@Data
public class ConsultData {
    private String status;
    private List<DatePoint> result;
}
@Data
class DatePoint{
    Date date;
    String value;
}