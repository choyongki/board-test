package study.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.board.file.UploadFile;
import study.board.domain.dto.FileDTO;

import java.util.List;

@Mapper
public interface FileMapper {
    void save(UploadFile uploadFile);

    List<FileDTO> select(String fileGroup);

    FileDTO findById(Long id);
}
