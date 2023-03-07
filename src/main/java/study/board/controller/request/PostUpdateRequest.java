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
public class PostUpdateRequest {
    private Long id;                            // 게시글 ID
    private String title;                       // 제목
    private String content;                     // 내용
    private String password;                    // 비밀번호
    private String noticeYn;                    // 공지글 여부
    private Long boardId;                       // 게시판 ID
    private List<MultipartFile> attachFiles;    // 첨부파일
    private String fileGroup;           // 파일 그룹키

    public PostDTO toPostDTO(){
        return PostDTO.of(id,title, content, password, noticeYn, boardId, fileGroup);
    }

}
