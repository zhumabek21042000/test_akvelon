package kz.com.dto;

import kz.com.entities.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Date;


/*
 ProjectRequest class is dedicated to transfer data to make it serializable, if we aimed to
 pass data from back-end to front-end, the way is to make it serializable
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProjectRequest implements Serializable {
    private Long id;
    private String name;
    private String start_date;
    private String end_date;
    private String status;
    private int priority;
}



