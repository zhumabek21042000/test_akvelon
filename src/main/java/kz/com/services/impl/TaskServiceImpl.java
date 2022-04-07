package kz.com.services.impl;

import kz.com.entities.Project;
import kz.com.entities.Task;
import kz.com.repositories.TaskRepository;
import kz.com.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public List<Task> getTasksByProject(Project project) {
        return taskRepository.findAllByProject(project);
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.findById(id).get();
    }
}
