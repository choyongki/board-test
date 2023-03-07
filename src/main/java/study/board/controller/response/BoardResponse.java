package study.board.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import study.board.domain.dto.BoardDTO;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class BoardResponse {
    private long id;                        // ID (PK)
    private String title;                   // 게시판 제목
    private String content;                 // 내용
    private String writerId;                // 작성자
    private LocalDateTime createdDate;      // 생성 날짜
    private LocalDateTime updatedDate;      // 수정 날짜

    public static BoardResponse fromBoardDTO(BoardDTO boardDTO){
        return new BoardResponse(
                boardDTO.getId(),
                boardDTO.getTitle(),
                boardDTO.getContent(),
                boardDTO.getWriterId(),
                boardDTO.getCreatedDate(),
                boardDTO.getUpdatedDate()
        );
    }
}
