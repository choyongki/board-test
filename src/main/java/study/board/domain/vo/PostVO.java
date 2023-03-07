package study.board.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class PostVO {
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

    public static PostVO of(String title, String content, String writerId, String password, String noticeYn,Long boardId,String fileGroup){
        return new PostVO(null, title, content, writerId, password, 0, noticeYn, null, null, null, boardId, fileGroup);
    }

    public static PostVO of(Long id, String title, String content, String password, String noticeYn){
        return new PostVO(id, title, content, null, password, 0, noticeYn, null, null, null, null, null);
    }




}
