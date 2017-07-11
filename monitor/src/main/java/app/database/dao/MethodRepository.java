package app.database.dao;

import app.database.domain.Monitor_method;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@Transactional
public interface MethodRepository extends JpaRepository<Monitor_method,Long> {
    List<Monitor_method> findTop10ByResourceOrderByIdDesc(String resource);
}
