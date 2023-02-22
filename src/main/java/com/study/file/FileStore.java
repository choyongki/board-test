package com.study.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@Component
public class FileStore {
    // 루트 경로 불러오기
    private final String rootPath = System.getProperty("user.dir");
    // 프로젝트 루트 경로에 있는 files 디렉토리
    private final String fileDir = rootPath + "/UploadFiles/files";

    public String getFullPath(String filename) { return fileDir + filename; }

    public UploadFile storeFile(MultipartFile multipartFile,String fileGroup) throws IOException {

        if(multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        // 작성자가 업로드한 파일명 -> 서버 내부에서 관리하는 파일명
        // 파일명을 중복되지 않게끔 UUID로 정하고 ".확장자"는 그대로
        String storedFilename = UUID.randomUUID() + "." + extractExt(originalFilename);

        log.info("originalFilename : {}",originalFilename);
        // 파일을 저장하는 부분 -> 파일경로 + storeFilename 에 저장
        String uri = getFullPath(originalFilename);
        // 확장자 호출
        String type = extractExt(originalFilename);

        UploadFile uploadFile = UploadFile.builder()
                .fileGroup(fileGroup)
                .originalFilename(originalFilename)
                .storedFilename(storedFilename)
                .size(multipartFile.getSize())
                .type(type)
                .uri(uri)
                .build();

        // 파일 저장
        multipartFile.transferTo(new File(getFullPath(storedFilename)));

        return uploadFile;
    }

    // 파일이 여러개 들어왔을 때 처리해주는 부분
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles,String fileGroup) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile,fileGroup));
            }
        }
        return storeFileResult;
    }

    // 확장자 추출
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


}
