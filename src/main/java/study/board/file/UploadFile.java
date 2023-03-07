package study.board.file;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadFile {
    private String fileGroup;       // 파일 그룹
    private String originalFilename;  // 작성자가 업로드한 파일명
    private String storedFilename;   // 서버 내부에서 관리하는 파일명
    private String uri; // 저장 경로
    private Long size;
    private String type; // 파일 타입
}
