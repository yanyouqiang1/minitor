package app.database.dao;

import app.database.domain.Minitor_group;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@Transactional
public interface ResourceRepository extends JpaRepository<Minitor_group,Long> {
}
