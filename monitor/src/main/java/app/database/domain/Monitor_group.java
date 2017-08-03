package app.database.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by Administrator on 2017/7/6.
 */
@Entity
@Data
public class Monitor_group {
    @Id
    @GeneratedValue
    private Long columnid;

    private Long id;

    private Long request_visitors,response_visitors;

    private Float rate;

    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Monitor_overall overall;

    public Monitor_group() {
    }
}
