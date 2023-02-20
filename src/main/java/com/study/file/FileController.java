package com.study.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/file")
@Controller
public class FileController {
    private final FileService fileService;
    private final FileStore fileStore;

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws MalformedURLException{
        log.info("FileController :: downloadfile");
        FileDTO file = fileService.getFile(id);

        log.info("file : {}",file.getOriginalFilename());
        String originalFilename = file.getOriginalFilename();
        String storedFilename = file.getStoredFilename();
        log.info(fileStore.getFullPath(originalFilename));

        UrlResource urlResource = new UrlResource("file:"+fileStore.getFullPath(storedFilename));

        log.info("UrlResource : {}",urlResource);
        // 업로드한 파일명이 한글인경우 한글이 깨질수 잇음
        String encodedUploadFileName = UriUtils.encode(originalFilename, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        // header에 CONTENT_DISPOSITION 설정을 통해 클릭 시 다운로드 진행
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);

    }

}
