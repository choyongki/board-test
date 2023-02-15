package com.study.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class CommentRequest {
    private Long postId;
    private Long parentId;
}
