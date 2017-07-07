package app.database.dao;

import app.database.domain.Mini_group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@Transactional
public interface GroupRepository extends JpaRepository<Mini_group,Long> {
    List<Mini_group> findTop10ByGroupNameOrderByIdDesc(String groupName);
}
