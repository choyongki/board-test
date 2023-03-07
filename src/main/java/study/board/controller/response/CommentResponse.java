package study.board.controller.response;

import lombok.*;
import study.board.domain.dto.CommentDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class CommentResponse {

    private Long id;
    private Long postId;
    private Long parentId;
    private String writerId;
    private String content;
    private LocalDateTime createdDate;
    private List<CommentResponse> childCommentList;


    public static CommentResponse fromCommentDTO(CommentDTO commentDTO){
        return new CommentResponse(
                commentDTO.getId(),
                commentDTO.getPostId(),
                commentDTO.getParentId(),
                commentDTO.getWriterId(),
                commentDTO.getContent(),
                commentDTO.getCreatedDate(),
                commentDTO.getChildCommentList().stream().map(CommentResponse::fromCommentDTO).collect(Collectors.toList())
        );
    }
}
