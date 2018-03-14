package app.database.dao;

import app.database.domain.Monitor_servicesumup;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ServiceSumupRepository extends JpaRepository<Monitor_servicesumup, Long> {
    public List<Monitor_servicesumup> findTop12ByServiceNameOrderByColumnidDesc(String serviceName);
}
