package app.database.dao;

import app.database.domain.Mini_method;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017/7/6.
 */
@Transactional
public interface MethodRepository extends JpaRepository<Mini_method,Long> {
}
