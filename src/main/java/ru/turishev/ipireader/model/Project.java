package ru.turishev.ipireader.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"tasks"})
@Builder
@Table(name = "projects_project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY)
    private List<Task> tasks;
}
