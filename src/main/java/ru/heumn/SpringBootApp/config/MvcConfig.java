package ru.heumn.SpringBootApp.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {


    public void addViewController(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("login");
    }
}
