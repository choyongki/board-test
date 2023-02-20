package com.study.file;

import com.study.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {

    private final FileMapper fileMapper;

    // 게시글 별 파일 전체 조회
    public List<FileDTO> getFileList(String fileGroup){
        return fileMapper.select(fileGroup);
    }

    // 파일 단건 조회
    public FileDTO getFile(Long id){
        return fileMapper.findById(id);
    }


    @Transactional
    // 파일 저장
    public void saveFile(List<UploadFile> uploadFiles){
        for (UploadFile uploadFile : uploadFiles) {
            fileMapper.save(uploadFile);
        }
    }
}
