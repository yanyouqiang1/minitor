package app.database.domain;

import app.database.service.ScheduleService;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/22 0022.
 */
@Entity
@Data
public class Schedule_service {
    @Id
    @GeneratedValue
    private long columnid;

    private String name;

    private int scale;

    private Date time;

    public Schedule_service(){
        time = new Date();
    }
}
