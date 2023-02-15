package com.study.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class CommentDTO extends CommonDTO {

    private Long id;

    private Long parentId;

    private String content;

    private String writerId;

    private LocalDateTime createdDate;

    private List<CommentDTO> childCommentList;

}
