package com.liumapp.ocr.third.idcard.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author liumapp
 * @file Base64File.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 6/14/18
 */
public class Base64File implements MultipartFile {

    private final byte[] content;

    private final String header;

    public Base64File(byte[] content, String header) {
        this.content = content;
        this.header = header.split(";")[0];
    }

    public byte[] getContent() {
        return content;
    }

    public String getHeader() {
        return header;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getOriginalFilename() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return new byte[0];
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {

    }
}
