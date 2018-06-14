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

    public AliOcr(String image, String url, String configure) {
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
}
