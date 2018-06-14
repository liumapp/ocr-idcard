package com.liumapp.ocr.third.idcard.ocr.ali;

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

    private String appKey;

    private String appSecret;

    /**
     * 图片二进制数据的base64编码
     */
    private String image;

    /**
     * api url
     */
    private String url;

    /**
     * json
     * {\"side\":\"face\"}"
     * 身份证正反面类型:face/back
     */
    private String configure;

    public AliOcr(String appKey, String appSecret, String image, String url, String configure) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.image = image;
        this.url = url;
        this.configure = configure;
    }

    public AliOcr() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getConfigure() {
        return configure;
    }

    public void setConfigure(String configure) {
        this.configure = configure;
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
}
