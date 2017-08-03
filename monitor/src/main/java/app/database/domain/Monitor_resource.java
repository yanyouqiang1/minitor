package app.database.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/7/11.
 */
@Entity
@Data
public class Monitor_resource {
    @Id
    @GeneratedValue
    private Long columnid;

    Long id;

    private Long request_visitors,response_visitors;

    Long groupId;

    String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Monitor_overall overall;

    public Monitor_resource() {
    }

}
