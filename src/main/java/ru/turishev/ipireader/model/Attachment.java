package ru.turishev.ipireader.model;

import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@ToString(exclude = {})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks_attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Column(name = "filename")
    private String filename;

    @Column(name = "original_filename")
    private String original_filename;

    @Column(name = "date_added")
    private Timestamp dateAdded;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private Task task;
}
