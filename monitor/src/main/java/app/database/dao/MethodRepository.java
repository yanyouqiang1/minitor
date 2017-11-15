package app.database.dao;

import app.database.domain.Monitor_method;
import app.database.domain.Monitor_overall;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@Transactional
public interface MethodRepository extends JpaRepository<Monitor_method,Long> {
    public Monitor_method findFirstByIdOrderByColumnid(long id);

    public List<Monitor_method> findTop90ByIdOrderByColumnidDesc(long id);

    public List<Monitor_method> findByResourceIdAndOverall(Pageable pageable, long resourceId, Monitor_overall overall);

    public List<Monitor_method> findAllByOverall(Pageable pageable,Monitor_overall overall);

    public List<Monitor_method> findAllByGroupidAndOverall(Pageable pageable, long groupid, Monitor_overall overall);

    public List<Monitor_method> findAllByServiceidAndOverall(Pageable pageable, long serviceid, Monitor_overall overall);

    public List<String> findDistinctNameByServiceid(long serviceId);

    public Monitor_method findFirstByServiceidAndNameOrderByColumnidDesc(long serviceID,String methodName);

    public List<Monitor_method> findTop90ByServiceidAndNameOrderByColumnidDesc(long serviceID,String methodName);

}
