package com.liumapp.ocr.third.idcard.config;

import com.liumapp.ocr.third.idcard.ocr.ali.AliOcr;
import com.liumapp.ocr.third.idcard.util.FileManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author liumapp
 * @file Config.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 6/13/18
 */
@Configuration
public class Config extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowCredentials(true);
    }

    @Bean
    @ConfigurationProperties(prefix = "liumapp.filemanager")
    public FileManager fileManager () {
        return new FileManager();
    }

    @Bean
    @ConfigurationProperties(prefix = "liumapp.ocr.ali")
    public AliOcr aliOcr () {
        return new AliOcr();
    }

}
