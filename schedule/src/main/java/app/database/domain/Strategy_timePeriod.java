package app.database.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/8.
 */
@Entity
@Data
public class Strategy_timePeriod {
    @Id
    @GeneratedValue
    private Integer id;

    private String serviceName;

    private String peekTime;

    private String thoughTime;

    private Boolean peekHandle;

    private Boolean thoughHandle;

    private Boolean onOrOff;

    private Date updateTime;

    public Strategy_timePeriod() {
        updateTime = new Date();
    }

    public Strategy_timePeriod(String serviceName) {
        this.serviceName = serviceName;
    }
}
