package ru.turishev.ipireader.dto;

import lombok.Builder;
import lombok.Data;
import ru.turishev.ipireader.model.Comment;
import ru.turishev.ipireader.utils.Utils;

@Data
@Builder
public class CommentDto {
	private String author;
	private String dateAdded;
	private String text;
	private boolean isComment;
	public static CommentDto from(Comment comment) {
		return CommentDto.builder()
				.author((comment.getAuthor()!=null)?comment.getAuthor().getFullName():null)
				.dateAdded(Utils.convertTimestampToString(comment.getDateAdded()))
				.text(comment.getContent().getText())
				.isComment(true)
				.build();
	}
}
