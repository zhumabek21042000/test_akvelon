package kz.com.services.impl;

import kz.com.entities.Project;
import kz.com.repositories.ProjectRepository;
import kz.com.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service // The annotation to make it also autowired and point out that it will serve as service
public class ProjectServiceImpl implements ProjectService { // To release the methods we will implement project service interface

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).get();
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    @Override
    public List<Project> findAllByName(String name) {
        return projectRepository.findAllByNameLike("%"+name+"%");
    }
}
