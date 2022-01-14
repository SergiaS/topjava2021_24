package ru.javawebinar.topjava.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.javawebinar.topjava.web.json.JacksonObjectMapper;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static ru.javawebinar.topjava.web.converter.DateTimeFormatters.*;

//@Configuration
//@EnableWebMvc
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//@ComponentScan(basePackages = {"ru.javawebinar.**.web"})
public class SpringMvcConfig
//        extends AppMainSpringConfig implements WebMvcConfigurer
{

//    @Bean(name = "objectMapper")
//    public ObjectMapper getMapper() {
//        return JacksonObjectMapper.getMapper();
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
////        WebMvcConfigurer.super.configureMessageConverters(converters);
//        converters.add(mappingJackson2HttpMessageConverter(getMapper()));
//        converters.add(stringHttpMessageConverter());
//    }
//
//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(
//            @Autowired ObjectMapper objectMapper) {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setObjectMapper(objectMapper);
//        return converter;
//    }
//
//    @Bean
//    public StringHttpMessageConverter stringHttpMessageConverter() {
//        StringHttpMessageConverter converter = new StringHttpMessageConverter();
//        converter.setSupportedMediaTypes(List.of(
//                new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8),
//                new MediaType(MediaType.TEXT_HTML, StandardCharsets.UTF_8)
//        ));
//        return converter;
//    }
//
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(authenticationPrincipalArgumentResolver());
//    }
//
//    @Bean
//    public AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver() {
//        return new AuthenticationPrincipalArgumentResolver();
//    }
//
//
//    @Bean(name = "conversionService")
//    public FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean(
//            @Autowired LocalTimeFormatter localTimeFormatter,
//            @Autowired LocalDateFormatter localDateFormatter
////            @Autowired StringHttpMessageConverter stringHttpMessageConverter,
////            @Autowired MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter
//    ) {
//        FormattingConversionServiceFactoryBean formatService = new FormattingConversionServiceFactoryBean();
//        formatService.setFormatters(Set.of(localTimeFormatter, localDateFormatter));
////        formatService.setConverters(Set.of(stringHttpMessageConverter, mappingJackson2HttpMessageConverter));
//        return formatService;
//    }
//
//    @Bean
//    public LocalTimeFormatter localTimeFormatter() {
//        return new LocalTimeFormatter();
//    }
//
//    @Bean
//    public LocalDateFormatter localDateFormatter() {
//        return new LocalDateFormatter();
//    }
//
//
//    @Bean
//    public Swagger2DocumentationConfiguration swagger2DocumentationConfiguration() {
//        return new Swagger2DocumentationConfiguration();
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // <mvc:resources mapping="/resources/**" location="/resources/"/>
//        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//        // <mvc:resources mapping="/webjars/**" location="classpath:/META-INF/resources/webjars/"/>
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//        // <mvc:resources mapping="swagger-ui.html" location="classpath:/META-INF/resources/" />
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//    }
//
//
//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/jsp/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }
//
//    @Value("#{systemEnvironment[TOPJAVA_ROOT]}")
//    private String TOPJAVA_ROOT_PATH;
//
//    @Bean(name = "messageSource")
//    public MessageSource reloadableResourceBundleMessageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setCacheSeconds(5);
//        messageSource.setDefaultEncoding("UTF-8");
//        messageSource.addBasenames("file:///" + TOPJAVA_ROOT_PATH + "/config/messages/app");
//        messageSource.setFallbackToSystemLocale(false);
//        return messageSource;
//    }
//
//    @Bean
//    public MessageSourceAccessor messageSourceAccessor(@Autowired MessageSource messageSource) {
//        return new MessageSourceAccessor(messageSource);
//    }
//
//    @Bean(name = "localeResolver")
//    public CookieLocaleResolver cookieLocaleResolver() {
//        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//        cookieLocaleResolver.setDefaultLocale(new Locale("ru"));
//        return cookieLocaleResolver;
//    }
//
//
//    @Bean(name = "localeChangeInterceptor")
//    public HandlerInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("lang");
//        return localeChangeInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
}
