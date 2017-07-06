package app.database.dao;

import app.database.domain.Mini_group;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017/7/6.
 */
@Transactional
public interface GroupRepository extends JpaRepository<Mini_group,Long> {
}
