package study.board.domain.dto;

import lombok.*;
import study.board.domain.vo.CommentVO;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class CommentDTO {

    private Long id;
    private Long postId;
    private Long parentId;
    private String writerId;
    private String content;
    private LocalDateTime createdDate;
    private List<CommentDTO> childCommentList;

    public static CommentDTO of(Long postId,Long parentId,String content,String writerId){
        return new CommentDTO(null,postId,parentId,writerId,content,null,null);
    }

    public static CommentDTO of(Long postId,Long parentId){
        return new CommentDTO(null,postId,parentId,null,null,null,null);
    }

    public CommentVO toCommentVO(){
        return CommentVO.of(postId,parentId,writerId,content);
    }

    public static CommentDTO fromCommentVO(CommentVO commentVO){
        return new CommentDTO(
                commentVO.getId(),
                commentVO.getPostId(),
                commentVO.getParentId(),
                commentVO.getWriterId(),
                commentVO.getContent(),
                commentVO.getCreatedDate(),
                null
        );
    }
}
