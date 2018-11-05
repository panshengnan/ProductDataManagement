package com.cgwx.ConfigurationSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebAppConfiguration extends WebMvcConfigurerAdapter {

    @Value("${productsStorePath}")
    private String productsStorePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/Path/**").addResourceLocations(productsStorePath);
        super.addResourceHandlers(registry);
    }
}




