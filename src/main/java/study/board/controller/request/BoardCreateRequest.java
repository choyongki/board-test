package study.board.controller.request;

import lombok.*;
import study.board.domain.dto.BoardDTO;

@Setter
@Getter
public class BoardCreateRequest {
    private Long id;
    private String title;
    private String content;
    private String writerId;

    public BoardDTO toBoardDTO(){
        return BoardDTO.of(
                title,
                content,
                writerId
        );
    }

}
