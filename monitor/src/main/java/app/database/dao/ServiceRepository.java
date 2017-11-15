package app.database.dao;

import app.database.domain.Monitor_overall;
import app.database.domain.Monitor_services;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@Transactional
public interface ServiceRepository extends JpaRepository<Monitor_services,Long> {
    public List<Monitor_services> findByOverall(Monitor_overall overall);

    public String findDistinctNameById(long id);

    public Long findDistinctIdByName(String serviceName);

    public Monitor_services findFirstDistinctIdByNameOrderByColumnid(String serviceName);

    public Monitor_services findFirstByNameOrderByColumnidDesc(String serviceName);

    public List<Monitor_services> findTop30ByNameOrderByColumnidDesc(String serviceName);
}
