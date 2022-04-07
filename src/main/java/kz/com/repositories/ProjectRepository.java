package kz.com.repositories;

import kz.com.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository // Repositories are designed to make SQL queries simple without writing sql request
@Transactional
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByNameLike(String name);
}
