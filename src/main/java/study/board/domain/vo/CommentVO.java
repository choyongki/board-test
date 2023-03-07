package study.board.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class CommentVO {
    private Long id;
    private Long postId;
    private Long parentId;
    private String writerId;
    private String content;
    private int depth;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static CommentVO of(Long postId,Long parentId,String writerId , String content){
        return new CommentVO(null,postId,parentId,writerId,content,0,null,null);
    }

    public static CommentVO of(Long postId,Long parentId){
        return new CommentVO(null,postId,parentId,null,null,0,null,null);
    }
}
