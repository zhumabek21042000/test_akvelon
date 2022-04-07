package kz.com.services;

import kz.com.entities.Project;
import kz.com.entities.Task;

import javax.transaction.Transactional;
import java.util.List;
/*
    Service interface is dedicated to create functionality of entities
 */
public interface ProjectService {
    List<Project> getAllProjects();
    Project addProject(Project project);
    Project getProjectById(Long id);
    void deleteProject(Project project);
    List<Project> findAllByName(String name);


}
