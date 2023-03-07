package study.board.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import study.board.domain.dto.PostDTO;

import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@Setter
@Getter
public class PostResponse {
    private Long id;                    // ID - pk
    private String title;               // 제목
    private String content;             // 내용
    private String writerId;            // 작성자
    private int viewCnt;                // 조회수
    private String noticeYn;            // 공지글 여부
    private LocalDateTime createdDate;  // 생성일
    private LocalDateTime modifiedDate; // 수정일
    private String fileGroup;           // 파일 그룹키  -> 나중에 file에 게시글 id를 저장하면 간편해 질것 같음.

    public static PostResponse fromPostDTO(PostDTO postDTO){
        return new PostResponse(
                postDTO.getId(),
                postDTO.getTitle(),
                postDTO.getContent(),
                postDTO.getWriterId(),
                postDTO.getViewCnt(),
                postDTO.getNoticeYn(),
                postDTO.getCreatedDate(),
                postDTO.getModifiedDate(),
                postDTO.getFileGroup()
        );
    }
}
