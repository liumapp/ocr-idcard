package com.liumapp.ocr.third.idcard.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author liumapp
 * @file FileManager.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 6/14/18
 */
@Component
public class FileManager {

    private String savePath;

    public void save (MultipartFile file) throws IOException {
        File destFile = new File(savePath + file.getOriginalFilename());
        file.transferTo(destFile);
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
