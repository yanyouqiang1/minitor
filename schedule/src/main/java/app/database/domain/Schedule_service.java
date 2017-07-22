package app.database.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
