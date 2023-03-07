package study.board.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.board.domain.dto.BoardDTO;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardVO {
    private Long id;                        // ID (PK)
    private String title;                   // 게시판 제목
    private String content;                 // 내용
    private String writerId;                // 작성자
    private LocalDateTime createdDate;      // 생성 날짜
    private LocalDateTime updatedDate;      // 수정 날짜

    public static BoardVO of(String title,String content,String writerId){
        return new BoardVO(
                null,
                title,
                content,
                writerId,
                null,
                null
        );
    }
}
