package app.database.dao;

import app.database.domain.Mini_overall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@Transactional
//@RepositoryRestResource(path = "overall")
public interface OverallRepository extends JpaRepository<Mini_overall, Long> {
    //    List<Mini_overall> findFirst10ByIdDesc();
    @Query("select o.id as id,o.method_put as method_put,o.method_post as method_post,o.method_get as method_get,o.method_delete as method_delete," +
            "o.visitors as visitors,o.groups as groups,o.response_max as response_max,o.response_min as response_min,o.resposne_avg as response_avg," +
            "o.status_200 as status_200,o.status_300 as status_300,o.status_400 as status_400,o.status_500 as status_500,o.TPS as TPS from Mini_overall o ")
    List<Mini_overall> findall1();
}
