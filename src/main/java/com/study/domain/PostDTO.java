package com.study.domain;

import com.study.file.UploadFile;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class PostDTO {
    private Long id;                       // PK
    private String title;                  // 제목
    private String content;                // 내용
    private String password;                // 비밀번호
    private String writerId;                 // 작성자
    private int viewCnt;                   // 조회 수
    private Boolean noticeYn;              // 공지글 여부
    private Boolean deleteYn;              // 삭제 여부
    private LocalDateTime createdDate;     // 생성일시
    private LocalDateTime modifiedDate;    // 최종 수정일시
    private UploadFile attachFile;          // 첨부파일
    private Long boardId; // 게시판

}
