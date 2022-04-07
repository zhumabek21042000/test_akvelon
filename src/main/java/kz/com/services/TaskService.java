package kz.com.services;

import kz.com.entities.Project;
import kz.com.entities.Task;

import javax.transaction.Transactional;
import java.util.List;

public interface TaskService {
    List<Task> getTasksByProject(Project project);
    Task addTask(Task task);
    void deleteTask(Task task);
    Task getTask(Long id);
}
