package com.study.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDeleteRequest {

    private int commentId;
    private int postId;
    private int boardId;
}
