package kz.com.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
    In the Project class, the required information is written clearly
 */
@Table(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER) // Since in the task condition said, that each project has multiple tasks
    // the relationship will be many tasks to one project, but one task belong to one project
    private Project project;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "description")
    private String description;

    @Column(name = "priority")
    private int priority;
}

