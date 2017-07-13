package app.database.dao;

import app.database.domain.Monitor_group;
import app.database.domain.Monitor_overall;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@Transactional
public interface GroupRepository extends JpaRepository<Monitor_group,Long> {

    public List<Monitor_group> findAllByOverall(Monitor_overall overall);

    public List<Monitor_group> findByOverall(Pageable pageable,Monitor_overall overall);
}
