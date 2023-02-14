package com.study.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BoardDTO {
    private Long id;             // PK
    private String title;        // 제목
    private String password;    // 비밀번호
    private String content;      // 내용
    private String writerId;       // 작성자
    private Boolean noticeYn;    // 공지글 여부
    private LocalDateTime createdDate;    // 생성일
    private LocalDateTime updatedDate;    // 수정일
    private Long boardId; // 게시판
}
