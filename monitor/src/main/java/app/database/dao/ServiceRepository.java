package app.database.dao;

import app.database.domain.Monitor_group;
import app.database.domain.Monitor_services;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017/7/6.
 */
@Transactional
public interface ServiceRepository extends JpaRepository<Monitor_services,Long> {
}
