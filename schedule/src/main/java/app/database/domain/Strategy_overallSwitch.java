package app.database.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/10.
 */
@Entity
@Data
public class Strategy_overallSwitch {
    @Id
    @GeneratedValue
    private Integer id;

    private String strategyName;

    private Boolean onOrOff;

    private Date updateTime;

    public Strategy_overallSwitch() {
        updateTime = new Date();
    }
}
