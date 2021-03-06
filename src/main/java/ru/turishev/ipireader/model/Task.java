package ru.turishev.ipireader.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"divisionsTopic","project","children","parent","createdBy","responsible","responsibleGroup","status",
        "priority", "fromDepend","toDepend","comments","spectrators","spectratorsGroup"})
@Builder
@Table(name = "tasks_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "subject")
    private String subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id")
    private DivisionsTopic divisionsTopic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "parent_task_id")
    private Task parent;

    @OneToMany(mappedBy = "parent" )
    private List<Task> children;

    @ManyToMany
    @JoinTable(name = "tasks_task_dependent_tasks",joinColumns = {@JoinColumn(name="from_task_id")}, inverseJoinColumns = {@JoinColumn(name = "to_task_id")})
    private List<Task> fromDepend;

    @ManyToMany
    @JoinTable(name = "tasks_task_dependent_tasks",joinColumns = {@JoinColumn(name="to_task_id")}, inverseJoinColumns = {@JoinColumn(name = "from_task_id")})
    private List<Task> toDepend;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private User responsible;

    @ManyToOne
    @JoinColumn(name = "responsible_group_id")
    private Group responsibleGroup;

    @OneToOne
    @JoinColumn(name = "status_id")
    private CommonStatus status;

    @OneToOne
    @JoinColumn(name = "priority_id")
    private CommonPriority priority;

    @Column(name = "date_added")
    private Timestamp dateAdded;

    @Column(name = "date_changed")
    private Timestamp dateChanged;

    @Column(name = "date_closed")
    private Timestamp dateClosed;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id")
    private Markup description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    @OrderBy("id asc")
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(name = "tasks_task_spectators", joinColumns = {@JoinColumn(name = "task_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> spectrators;

    @ManyToMany
    @JoinTable(name = "tasks_task_spectator_groups", joinColumns = {@JoinColumn(name = "task_id")}, inverseJoinColumns = {@JoinColumn(name = "group_id")})
    private List<Group> spectratorsGroup;
        
    @OneToMany(mappedBy = "task")
    private List<Attachment> attachments;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "broken_reaction_level")
    private Long brokenReactionLevel;

    @Column(name = "severity_id")
    private Long severity;

    @Column(name = "marked_as_expired")
    private boolean markedExpired;

    @Column(name = "close_if_all_child_tasks_are_closed")
    private boolean closeIfAllChildClosed;

    @Column(name = "anonymous_reporter")
    private String anonymousReporter;

    @Column(name = "status_change_reason")
    private String statusChangeReason;
}
