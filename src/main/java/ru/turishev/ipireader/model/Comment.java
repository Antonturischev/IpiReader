package ru.turishev.ipireader.model;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@ToString(exclude = {"author"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Column(name = "date_added")
    private Timestamp dateAdded;

    @OneToOne
    @JoinColumn(name = "content_id")
    private Markup content;
}
