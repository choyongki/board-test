package study.board.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.board.domain.dto.FileDTO;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FileResponse {
    private Long id;                // 파일인덱스
    private String fileGroup;       // 파일 그룹
    private String originalFilename;  // 작성자가 업로드한 파일명
    private String storedFilename;   // 서버 내부에서 관리하는 파일명
    private String uri; // 저장 경로
    private Long size;
    private String type; // 파일 타입

    public static FileResponse fromFileDTO(FileDTO fileDTO){
        return new FileResponse(
                fileDTO.getId(),
                fileDTO.getFileGroup(),
                fileDTO.getOriginalFilename(),
                fileDTO.getStoredFilename(),
                fileDTO.getUri(),
                fileDTO.getSize(),
                fileDTO.getType()
        );
    }
}
