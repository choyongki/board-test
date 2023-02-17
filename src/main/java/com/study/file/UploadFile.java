package com.study.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadFile {
    private String uploadFilename;  // 작성자가 업로드한 파일명
    private String storeFilename;   // 서버 내부에서 관리하는 파일명
}
