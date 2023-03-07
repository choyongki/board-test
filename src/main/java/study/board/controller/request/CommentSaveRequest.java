package study.board.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.board.domain.dto.CommentDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CommentSaveRequest {
    private Long postId;
    private Long boardId;
    private Long parentId;
    private String content;
    private String writerId;

    public CommentDTO toCommentDTO(){
        return CommentDTO.of(postId,parentId,writerId,content);
    }
}
