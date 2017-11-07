package app.database.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/9.
 */
@Entity
@Data
public class Strategy_visitorAverage {
    @Id
    @GeneratedValue
    private Integer id;

    private String methodName;

    private Long upper;

    private Long lower;

    private Boolean onOrOff;

    private Date updateTime;

    public Strategy_visitorAverage() {
        updateTime = new Date();
    }
}
