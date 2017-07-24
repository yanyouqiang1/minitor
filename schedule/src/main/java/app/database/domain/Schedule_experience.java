package app.database.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/7/24.
 */
@Data
@Entity
public class Schedule_experience {
    @Id
    @GeneratedValue
    private long columnid;

    private String source;

    private String target;


}
