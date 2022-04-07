package kz.com.dto;

import kz.com.entities.Project;
import kz.com.entities.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


/*
 TaskRequest class is dedicated to transfer data to make it serializable, if we aimed to
 pass data from back-end to front-end, the way is to make it serializable
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest implements Serializable {
    private Long id;
    private String name;
    private Project project;
    private String status;
    private String description;
    private int priority;
}


