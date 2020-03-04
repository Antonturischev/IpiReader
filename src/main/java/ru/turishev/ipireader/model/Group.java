package ru.turishev.ipireader.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_group")
@ToString(exclude = {"users","responsTasks"})
public class Group {
    @Id
    @Column(name = "codename")
    private String codename;
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_group_users", joinColumns = {@JoinColumn(name = "group_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    List<User> users;

    @OneToMany(mappedBy = "responsibleGroup",fetch = FetchType.LAZY)
    private List<Task> responsTasks;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tasks_task_spectator_groups", joinColumns = {@JoinColumn(name = "group_id")}, inverseJoinColumns = {@JoinColumn(name = "task_id")})
    private List<Task> spectTask;
}
