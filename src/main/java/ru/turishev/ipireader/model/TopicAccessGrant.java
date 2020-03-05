package ru.turishev.ipireader.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"topic"})
@Table(name = "divisions_topicaccessgrants")
public class TopicAccessGrant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "topic_id")
	private DivisionsTopic topic;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "group_id")	
	private Group group;
	
	@Column(name = "is_manager")
	private boolean is_manager;
	
	@Column(name = "is_spectator")
	private boolean is_spectator;
	
}
