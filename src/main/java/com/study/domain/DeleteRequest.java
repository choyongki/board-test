package com.study.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class DeleteRequest {
    private Long id;
    private Long boardId;
}
