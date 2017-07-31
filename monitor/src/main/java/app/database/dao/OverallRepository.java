package app.database.dao;

import app.database.domain.Monitor_overall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Administrator on 2017/7/6.
 */
@Transactional
public interface OverallRepository extends JpaRepository<Monitor_overall, Long> {
    public Monitor_overall findFirstByIdIsNotNullOrderByIdDesc();
    public List<Monitor_overall> findTop90ByIdIsNotNullOrderByIdDesc();

    public void deleteAllByCreateTimeBefore(Date date);
    public void deleteAllByIdNotNull();
}
