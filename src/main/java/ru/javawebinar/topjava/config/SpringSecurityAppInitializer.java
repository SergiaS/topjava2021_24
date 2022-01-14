package ru.javawebinar.topjava.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//@EnableWebSecurity
//@Configuration
public class SpringSecurityAppInitializer
//        extends AbstractAnnotationConfigDispatcherServletInitializer
        implements WebApplicationInitializer
{
    public void onStartup(ServletContext servletContext) throws ServletException {


        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppMainSpringConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));


        // security filter
        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");

        // Manage the lifecycle of the root application context
        XmlWebApplicationContext webContext = new XmlWebApplicationContext();
        webContext.setConfigLocation("spring/spring-mvc.xml");
        webContext.setServletContext(servletContext);


        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(webContext));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[] { SecurityConfig.class };
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[0];
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[0];
//    }
}
