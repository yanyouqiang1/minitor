package wang.jeese.springcloud.gateway.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jeese on 2017/5/31.
 */
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findAllByIsActiveIsTrue();
}
