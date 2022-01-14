package ru.javawebinar.topjava.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"ru.javawebinar.**.service"})
@ImportResource(locations = {"classpath:spring/spring-db.xml", "classpath:spring/spring-cache.xml", "classpath:spring/spring-security.xml"})
@EnableWebMvc
//@Import({SpringSecurityConfig.class})
public class AppMainSpringConfig {

    public AppMainSpringConfig() {
        System.out.println("BOOM");
    }


}

