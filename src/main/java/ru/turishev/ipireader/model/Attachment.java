package ru.turishev.ipireader.model;

import lombok.*;

import javax.persistence.*;

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

}
