package com.liumapp.ocr.third.idcard.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liumapp.ocr.third.idcard.entity.IdCard;
import com.liumapp.ocr.third.idcard.ocr.ali.AliOcr;
import com.liumapp.ocr.third.idcard.util.FileManager;
import com.liumapp.ocr.third.idcard.util.HttpUtil;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private AliOcr aliOcr;

    @Autowired
    private HttpUtil httpUtil;

    @RequestMapping("/")
    public String upload (@RequestBody IdCard idCard) {
        try {
            MultipartFile file = fileManager.base64toMultipart(idCard.getBase64file());
            fileManager.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString("save file failed");
        }
        return JSON.toJSONString("success");
    }

    @RequestMapping("/check")
    public String check (@RequestBody IdCard idCard) {
        try {
            MultipartFile file = fileManager.base64toMultipart(idCard.getBase64file());
            JSONObject object = new JSONObject();
            object.put("image", idCard.getBase64file());
            object.put("configure", aliOcr.getJSONConfigure());
            HttpResponse response = httpUtil.doPost();

        }
        return JSON.toJSONString("success");
    }

}
