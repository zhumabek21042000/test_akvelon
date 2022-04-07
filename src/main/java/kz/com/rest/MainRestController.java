package kz.com.rest;


import kz.com.dto.ProjectRequest;
import kz.com.dto.TaskRequest;
import kz.com.entities.Project;
import kz.com.entities.ProjectStatus;
import kz.com.entities.Task;
import kz.com.entities.TaskStatus;
import kz.com.services.ProjectService;
import kz.com.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // the dedicated origin can be any server
@RequestMapping("/api") // by the default, if we create Web API application, the request mapping is /api as usually it is
public class MainRestController {

    @Autowired
    private TaskService taskService; // Autowired is used to create objects without declaring them

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects") // Get Request is to get all projects from the DB
    public ResponseEntity<?> getAllProjects(){
        List<Project> projectList = projectService.getAllProjects(); // Since there are a lot of objects,
        // they will be in list, after that will be responsed to front-end server
        return ResponseEntity.ok(projectList);
    }

    @GetMapping("/project/{id}/tasks")
    public ResponseEntity<?> getTasks(@PathVariable(name = "id") Long id){ // to get the project by id, there will be
        // used ID path variable, after that the Project instance will be created and by the projectService instance
        // we can get the project by ID
        Project project = projectService.getProjectById(id);
        if(project != null){ // checking if it is not empty
            List<Task> taskList = taskService.getTasksByProject(project);
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // in case of emptiness, nothing will be returned
    }

    @ResponseBody
    @GetMapping("/project/{id}")
    public ResponseEntity<?> getProject(@PathVariable(name = "id") Long id){
        if(id !=null){
            Project project = projectService.getProjectById(id);
            if(project != null){
                return ResponseEntity.ok(project);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/addtask/{id}") // Post mapping dedicated to add something to database, in this way, we will add
    // task to the project and will be saved in DB
    public ResponseEntity<?> addTask(@PathVariable(name = "id") Long projectId, @RequestBody TaskRequest task){
        // Request Body is to get from front-end server requested object and make data transfering object
        if(projectId != null){ // checking if the id is not empty
            Project project = projectService.getProjectById(projectId);
            Task ts = new Task();
            if(project != null){ // if the project is not empty or null
                ts.setPriority(task.getPriority()); // in if case, the
                ts.setProject(project);
                ts.setName(task.getName());
                ts.setDescription(task.getDescription());
                TaskStatus tss = TaskStatus.getTaskStatusFromCode(task.getStatus());
                ts.setStatus(tss);
                taskService.addTask(ts); // add task to project and save in DB
               return ResponseEntity.ok(task);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deletetask/{id}") // DeleteMapping to delete object by getting ID of the task
    public ResponseEntity<?> deleteTask(@PathVariable(name = "id") Long taskId){
        Task task = taskService.getTask(taskId);
        if(task != null){
            taskService.deleteTask(task);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/edittask/{id}") // PutMapping is one of the CRUD operation to make changes in database
    // in this case, to edit task, the ID of task and requested body are required
    public ResponseEntity<?> editTask(@PathVariable(name = "id") Long taskId, @RequestBody TaskRequest task){
        Task ts = taskService.getTask(taskId);
        if(ts != null){ // Searching task by using task service, if task is not null
            ts.setName(task.getName()); // Here saves the new data to existing object
            ts.setDescription(task.getDescription());
            ts.setPriority(task.getPriority());
            TaskStatus tstat = TaskStatus.getTaskStatusFromCode(task.getStatus());
            ts.setStatus(tstat);
            taskService.addTask(ts);
            return ResponseEntity.ok(ts);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/addproject")
    public ResponseEntity<?> addProject(@RequestBody ProjectRequest projectRequest) throws ParseException {
        Project project = new Project();
        ProjectStatus ps = ProjectStatus.getProjectStatusFromCode(projectRequest.getStatus());
        project.setStatus(ps);
        project.setName(projectRequest.getName());
        project.setPriority(projectRequest.getPriority());

        java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(projectRequest.getStart_date());
        java.sql.Date date1 = new java.sql.Date(utilDate.getTime());
        project.setStart_date(date1);

        utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(projectRequest.getEnd_date());
        date1 = new java.sql.Date(utilDate.getTime());
        project.setEnd_date(date1);
        projectService.addProject(project);
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/deleteproject/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id){
        Project project = projectService.getProjectById(id);
        if(project != null){
            projectService.deleteProject(project);
            return ResponseEntity.ok(project);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/editproject/{id}")
    public ResponseEntity<?> editProject(@PathVariable(name = "id") Long id,
                                         @RequestBody ProjectRequest projectRequest) throws ParseException {
        System.out.println("12312312344343433");
        Project project = projectService.getProjectById(id);
        if(project != null){
            System.out.println("123123123");
            project.setName(projectRequest.getName());
            project.setPriority(projectRequest.getPriority());
            java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(projectRequest.getStart_date());
            java.sql.Date date1 = new java.sql.Date(utilDate.getTime());

            project.setStart_date(date1);
            utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(projectRequest.getEnd_date());
            date1 = new java.sql.Date(utilDate.getTime());
            project.setEnd_date(date1);

            ProjectStatus ps = ProjectStatus.getProjectStatusFromCode(projectRequest.getStatus());
            project.setStatus(ps);
            projectService.addProject(project);
            return ResponseEntity.ok(project);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search-project") // Searching filter for project
    public ResponseEntity<?> getSearchedProjects(@RequestParam(name = "text") String text){
        List<Project> projectList = projectService.findAllByName(text);
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }
}
