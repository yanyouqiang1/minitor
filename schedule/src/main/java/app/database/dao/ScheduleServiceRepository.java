package app.database.dao;

import app.database.domain.Schedule_service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22 0022.
 */
public interface ScheduleServiceRepository extends JpaRepository<Schedule_service,Long> {
    public List findTop90ByName(String serviceName);
}
