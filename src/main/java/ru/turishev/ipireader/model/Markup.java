package ru.turishev.ipireader.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@ToString(exclude = {})
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "markup_markup")
public class Markup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "html")
    private String text;

    @Column(name = "metadata")
    private String metadata;
}
