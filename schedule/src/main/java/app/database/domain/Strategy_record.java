package app.database.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Strategy_record {
    @Id
    @GeneratedValue
    private Integer id;

    private Boolean isAuto;

    private String strategyName;

    private String ServiceName;

    private String effect;

    private Date time;

}
