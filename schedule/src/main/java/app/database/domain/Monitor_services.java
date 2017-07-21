package app.database.domain;

import lombok.Data;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
@Data
public class Monitor_services {

    Long id;
    String name;
    Long visitors;
    Integer response_min,response_max,response_avg;

}
