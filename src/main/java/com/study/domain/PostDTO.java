package com.study.domain;

import com.study.file.UploadFile;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class PostDTO {
    private Long id;                       // PK
    private String fileGroup;              // 파일그룹
    private String title;                  // 제목
    private String content;                // 내용
    private String password;                // 비밀번호
    private String writerId;                 // 작성자
    private int viewCnt;                   // 조회 수
    private String noticeYn;              // 공지글 여부
    private Boolean deleteYn;              // 삭제 여부
    private LocalDateTime createdDate;     // 생성일시
    private LocalDateTime modifiedDate;    // 최종 수정일시
    private List<UploadFile> attachFile;          // 첨부파일
    private Long boardId; // 게시판


}
