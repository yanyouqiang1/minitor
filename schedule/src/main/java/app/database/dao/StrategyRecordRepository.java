package app.database.dao;

import app.database.domain.Strategy_container;
import app.database.domain.Strategy_record;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 */
public interface StrategyRecordRepository extends JpaRepository<Strategy_record,Integer> {
    public List<Strategy_record> findByisAutoEquals(Pageable pageable, boolean isAuto);
}
