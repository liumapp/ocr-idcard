package com.liumapp.ocr.third.idcard.ocr.ali;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file AliOcr.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 6/14/18
 */
@Component
public class AliOcr {

    private String appCode;

    private String appKey;

    private String appSecret;

    private String methods = "POST";

    /**
     * 图片二进制数据的base64编码
     */
    private String image;

    private String host;

    private String path;

    /**
     * json
     * {\"side\":\"face\"}"
     * 身份证正反面类型:face/back
     */
    private String configure;

    public AliOcr(String appCode, String appKey, String appSecret, String methods, String image, String host, String path, String configure) {
        this.appCode = appCode;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.methods = methods;
        this.image = image;
        this.host = host;
        this.path = path;
        this.configure = configure;
    }

    public AliOcr() {
    }

    public String getJSONConfigure () {
        JSONObject object = new JSONObject();
        object.put("side", configure);
        return JSON.toJSONString(object);
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getConfigure() {
        return configure;
    }

    public void setConfigure(String configure) {
        this.configure = configure;
    }
}
