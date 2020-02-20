package ru.turishev.ipireader.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"parent","children","responsibleUser","tasks"})
@Table(name = "divisions_topic")
public class DivisionsTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private DivisionsTopic parent;

    @OneToMany(mappedBy = "parent",fetch = FetchType.LAZY)
    private List<DivisionsTopic> children;

    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private User responsibleUser;

    @Column(name = "mptt_level")
    private Integer mpttLevel;

    @OneToMany(mappedBy = "divisionsTopic", fetch = FetchType.LAZY)
    private List<Task> tasks;
}
