package com.study.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class BoardCreateDTO {
    private Long id;
    private String title;
    private String content;
    private String writerId;
}
