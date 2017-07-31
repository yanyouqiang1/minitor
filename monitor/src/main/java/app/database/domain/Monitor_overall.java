package app.database.domain;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/6.
 */
@Entity
@Data
public class Monitor_overall {
    @Id
    @GeneratedValue
    private Long id;

    private Long response_visitors;

    private Long status_100,status_200,status_300,status_400,status_500;

    private Float rate_status_100,rate_status_200, rate_status_300, rate_status_400, rate_status_500;

    private Integer response_min,response_max,response_avg,response_median,response_90,response_99;

    private Long TPS;

    private Long request_visitors;

    private Date createTime;

    @OneToMany(mappedBy = "overall")
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Cascade(value = {CascadeType.ALL})
    private Set<Monitor_group> groups = new HashSet<Monitor_group>();

    @OneToMany(mappedBy = "overall")
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Cascade(value = {CascadeType.ALL})
    private Set<Monitor_resource> resources = new HashSet<Monitor_resource>();

    @OneToMany(mappedBy = "overall")
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Cascade(value = {CascadeType.ALL})
    private Set<Monitor_method> methods = new HashSet<Monitor_method>();

    @OneToMany(mappedBy = "overall")
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Cascade(value = {CascadeType.ALL})
    private Set<Monitor_services> services = new HashSet<Monitor_services>();

    public Monitor_overall() {
        createTime = new Date();
    }

}
