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
public class Strategy_visitorLimit {
    @Id
    @GeneratedValue
    private Integer id;

    private String serviceName;

    private Long upper;

    private Long lower;

    private Boolean onOrOff;

    private Date updateTime;

    public Strategy_visitorLimit() {
        updateTime = new Date();
    }

    public Strategy_visitorLimit(String serviceName) {
        this.serviceName = serviceName;
    }
}
