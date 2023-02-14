package com.study.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    private Long id;             // PK
    private String title;        // 제목
    private String content;      // 내용
    private String password;      // 비밀번호
    private String writerId;       // 작성자
    private Boolean noticeYn;    // 공지글 여부

    private Long boardId; // 게시판

}
