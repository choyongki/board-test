package study.board.domain.dto;

import lombok.*;
import study.board.domain.vo.BoardVO;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class BoardDTO {
    private Long id;                        // ID (PK)
    private String title;                   // 게시판 제목
    private String content;                 // 내용
    private String writerId;                // 작성자
    private LocalDateTime createdDate;      // 생성 날짜
    private LocalDateTime updatedDate;      // 수정 날짜


    public static BoardDTO of(String title,String content,String writerId){
        return new BoardDTO(null,title,content,writerId,null,null);
    }

    public static BoardDTO fromBoardVO(BoardVO boardVO){
        return new BoardDTO(
                boardVO.getId(),
                boardVO.getTitle(),
                boardVO.getContent(),
                boardVO.getWriterId(),
                boardVO.getCreatedDate(),
                boardVO.getUpdatedDate()
        );
    }

    public BoardVO toBoardVO(){
        return BoardVO.of(
                title,
                content,
                writerId
        );
    }

}
