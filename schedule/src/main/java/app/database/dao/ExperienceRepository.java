package app.database.dao;

import app.database.domain.Schedule_experience;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/7/24.
 */
public interface ExperienceRepository extends JpaRepository<Schedule_experience,Long> {
    public void deleteBySource(String source);
}
