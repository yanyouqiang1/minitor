package app.database.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/19.
 */
@Entity
@Data
public class Strategy_container {
    @Id
    @GeneratedValue
    private Integer id;

    private String serviceName;

    private int scale;

    private Date updateTime;

    public Strategy_container() {
        updateTime = new Date();
    }
}
