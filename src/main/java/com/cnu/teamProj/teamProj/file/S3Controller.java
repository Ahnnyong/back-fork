package com.cnu.teamProj.teamProj.file;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;
    private final static Logger logger = LoggerFactory.getLogger(S3Controller.class);

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) {
        try{
            String fileUrl = s3Service.uploadFile(file);
            return new ResponseEntity<>(String.format("파일 업로드에 성공! url 정보 : %s", fileUrl), HttpStatus.OK);
        } catch(IOException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("파일 업로드에 실패하셨습니다", HttpStatus.BAD_REQUEST);
        }
    }
}
