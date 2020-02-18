package ru.turishev.ipireader.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_user")
@ToString(exclude = {"higherUser","underUsers","groups","respTopics","createdTasks","responsTasks",
            "spectTasks"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String login;

    private String password="123456";

    @Column(name = "fullname")
    private String fullname;

    @OneToMany(mappedBy = "higherUser", fetch = FetchType.LAZY)
    private List<User> underUsers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "higherUser_id")
    private User higherUser;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_group_users", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "group_id")})
    private List<Group> groups = new ArrayList<Group>();

    @OneToMany(mappedBy = "responsibleUser",fetch = FetchType.LAZY)
    private List<DivisionsTopic> respTopics;

    @OneToMany(mappedBy = "createdBy")
    private List<Task> createdTasks;

    @OneToMany(mappedBy = "responsible")
    private List<Task> responsTasks;

    @ManyToMany
    @JoinTable(name = "tasks_task_spectators", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "task_id")})
    private List<Task> spectTasks;
}
