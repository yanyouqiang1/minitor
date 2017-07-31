package app.database.dao;

import app.database.domain.Monitor_group;
import app.database.domain.Monitor_overall;
import app.database.domain.Monitor_resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@Transactional
public interface ResourceRepository extends JpaRepository<Monitor_resource,Long> {
    public List<Monitor_resource> findByGroupIdAndOverall(Pageable pageable, long id, Monitor_overall overall);

    public String findDistinctNameById(long id);
}
