package app.database.dao;

import app.database.domain.Monitor_group;
import app.database.domain.Monitor_resource;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017/7/6.
 */
@Transactional
public interface ResourceRepository extends JpaRepository<Monitor_resource,Long> {

}
