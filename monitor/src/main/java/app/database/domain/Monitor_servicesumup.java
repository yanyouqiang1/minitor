package app.database.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Monitor_servicesumup {
    @Id
    @GeneratedValue
    private Long columnid;

    String serviceName;
    Long counts;

    public Monitor_servicesumup() {
    }
}
