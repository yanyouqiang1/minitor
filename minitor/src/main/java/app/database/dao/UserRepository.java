package app.database.dao;

import app.database.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameLike(String name);

}