package app.database.dao;

import app.database.domain.Mini_overall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Administrator on 2017/7/6.
 */
@Transactional
//@RepositoryRestResource(path = "overall")
public interface OverallRepository extends JpaRepository<Mini_overall, Long> {
    @Query("select o from Mini_overall o where o.id>?1 and o.id<=?2 order by id desc ")
    List getBetween(long left,long right);
}
