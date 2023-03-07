package study.board.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import study.board.domain.dto.PostDTO;

import java.util.List;

@ToString
@Setter
@Getter
public class PostCreateRequest {
    private String title;                       // 제목
    private String content;                     // 내용
    private String writerId;                    // 작성자
    private String password;                    // 비밀번호
    private String noticeYn;                    // 공지글 여부
    private Long boardId;                       // 게시판 ID
    private List<MultipartFile> attachFiles;    // 첨부파일

    public PostDTO toPostDTO(String fileGroup){
        return PostDTO.of(
                title,
                content,
                writerId,
                password,
                noticeYn,
                boardId,
                fileGroup
        );
    }

}
