package app.database.dao;

import app.database.domain.Monitor_method;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
@Transactional
public interface MethodRepository extends JpaRepository<Monitor_method,Long> {
    @Query("select distinct id from Monitor_method where serviceid=?1")
    public long[] findDistinctIdByServiceidEquals(Long serviceid);

    public List<Monitor_method> findTop30ByIdOrderByColumnidDesc(Long methodid);
}
