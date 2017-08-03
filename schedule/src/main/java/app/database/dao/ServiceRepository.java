package app.database.dao;

import app.database.domain.Monitor_services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
@Transactional
public interface ServiceRepository extends JpaRepository<Monitor_services,Long> {
    @Query("select distinct id from Monitor_services where name=?1")
    public long findDistinctIdFirstByNameEquals(String servcieName);

    @Query("select distinct id from Monitor_services")
    public List<Long> findDistinctIdByColumnidNotNull();

    public List<Monitor_services> findTop90ByIdEquals(Long id);

    public List<Monitor_services> findTop90ByNameEqualsOrderByColumnidDesc(String serviceName);
}
