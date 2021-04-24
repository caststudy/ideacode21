package com.onlyboot;

import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RestController
@SpringBootApplication
@MapperScan(basePackages = "com.onlyboot.mapper")
@ServletComponentScan(basePackages = "com.onlyboot.filter")
public class OnlyBoot //{
        implements WebMvcConfigurer {
    Logger log = Logger.getLogger(OnlyBoot.class);

    @RequestMapping("/test")
    public Map select(@RequestParam Map map) {
        return map;
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlyBoot.class, args);
    }

    @Value("${spring.servlet.multipart.location}")
    String upload;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("+++++++++ static resource  config........................" + upload);
        registry.addResourceHandler("/images/**").addResourceLocations("file:///" + upload);

    }

}
