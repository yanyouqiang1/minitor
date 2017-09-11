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
public class Strategy_responseTime {
    @Id
    @GeneratedValue
    private Integer id;

    private String methodName;

    private Integer upper;

    private Integer lower;

    private Integer upperLimit;

    private Date updateTime;

    public Strategy_responseTime() {
        updateTime = new Date();
    }
}
