package com.liumapp.ocr.third.idcard.controller;

import com.alibaba.fastjson.JSON;
import com.liumapp.ocr.third.idcard.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author liumapp
 * @file UploadController.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 6/14/18
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private FileManager fileManager;

    @RequestMapping("/")
    public String upload (@RequestParam("file") MultipartFile file) {
        try {
            fileManager.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString("save file failed");
        }
        return JSON.toJSONString("success");
    }

}
