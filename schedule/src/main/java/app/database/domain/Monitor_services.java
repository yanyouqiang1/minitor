package app.database.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/7/11.
 */
@Entity
@Data
public class Monitor_services {
    @Id
    @GeneratedValue
    private Long columnid;

    private Long id;
    private String name;
    private Long visitors;

    private Integer response_min,response_max,response_avg;

    public Monitor_services() {
    }
}
