package study.board.domain.dto;

import lombok.*;
import study.board.domain.vo.BoardVO;
import study.board.domain.vo.PostVO;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class PostDTO {
    private Long id;                    // ID - pk
    private String title;               // 제목
    private String content;             // 내용
    private String writerId;            // 작성자
    private String password;            // 비밀번호
    private int viewCnt;                // 조회수
    private String noticeYn;            // 공지여부
    private String deleteYn;            // 삭제여부
    private LocalDateTime createdDate;  // 생성일
    private LocalDateTime modifiedDate; // 수정일
    private Long boardId;               // 게시판 ID
    private String fileGroup;           // 첨부파일 그룹키


    public static PostDTO of(String title, String content, String writerId,String password,String noticeYn,Long boardId,String fileGroup){
        return new PostDTO(null,title,content,writerId,password,0,noticeYn,null,null,null,boardId,fileGroup);
    }

    public static PostDTO of(String title, String content, String password,String noticeYn,Long boardId,String fileGroup){
        return new PostDTO(null,title,content,null,password,0,noticeYn,null,null,null,boardId,fileGroup);
    }

    public static PostDTO of(Long id, String title, String content, String password,String noticeYn,Long boardId,String fileGroup){
        return new PostDTO(id,title,content,null,password,0,noticeYn,null,null,null,boardId,fileGroup);
    }



    public static PostDTO fromPostVO(PostVO postVO){
        return new PostDTO(
                postVO.getId(),
                postVO.getTitle(),
                postVO.getContent(),
                postVO.getWriterId(),
                postVO.getPassword(),
                postVO.getViewCnt(),
                postVO.getNoticeYn(),
                postVO.getDeleteYn(),
                postVO.getCreatedDate(),
                postVO.getModifiedDate(),
                postVO.getBoardId(),
                postVO.getFileGroup()
        );
    }

    public PostVO toPostVO(){
        return PostVO.of(title, content, writerId, password, noticeYn, boardId, fileGroup);
    }

    public PostVO toPostUpdateVO(){
        return PostVO.of(id, title, content,password, noticeYn);
    }



}
