package app.database.dao;

import app.database.domain.Minitor_method;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@Transactional
public interface MethodRepository extends JpaRepository<Minitor_method,Long> {
    List<Minitor_method> findTop10ByResourceOrderByIdDesc(String resource);
}
