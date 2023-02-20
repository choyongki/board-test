package com.study.mapper;

import com.study.file.FileDTO;
import com.study.file.UploadFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    void save(UploadFile uploadFile);

    List<FileDTO> select(String fileGroup);

    FileDTO findById(Long id);

}
