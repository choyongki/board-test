package com.study.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentSaveRequest {
    private int postId;
    private int parentId;
    private String content;
    private String writerId;
}
