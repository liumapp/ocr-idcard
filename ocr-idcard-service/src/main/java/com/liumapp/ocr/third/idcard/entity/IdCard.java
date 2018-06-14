package com.liumapp.ocr.third.idcard.entity;

import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file IdCard.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 6/14/18
 */
@Component
public class IdCard {

    private String base64file;

    public IdCard() {

    }

    public IdCard(String base64file) {
        this.base64file = base64file;
    }

    public String getBase64file() {
        return base64file;
    }

    public void setBase64file(String base64file) {
        this.base64file = base64file;
    }
}
