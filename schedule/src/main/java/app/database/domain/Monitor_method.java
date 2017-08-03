package app.database.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/7/6.
 */
@Entity
@Data
public class Monitor_method {
    @Id
    @GeneratedValue
    private Long columnid;

    private Long id;

    private Long resourceId;

    private Long groupid;

    private Long serviceid;

    private Long request_visitors,response_visitors;

    private Long status_100,status_200,status_300,status_400,status_500;

    private Float rate_status_100,rate_status_200, rate_status_300, rate_status_400, rate_status_500;

    private Integer response_min, response_max, response_avg,response_median,response_90,response_99;

    private Long TPS;

    private String name;

    public Monitor_method() {
    }

}
