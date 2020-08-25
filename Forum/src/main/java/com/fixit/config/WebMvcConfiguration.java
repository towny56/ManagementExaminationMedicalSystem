package com.fixit.config;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import com.fixit.interceptors.DynamicContentLoader;
import com.fixit.interceptors.LoggerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final DynamicContentLoader dynamicContentLoader;

    private final LoggerInterceptor loggerInterceptor;

    public WebMvcConfiguration(DynamicContentLoader dynamicContentLoader, LocaleChangeInterceptor localeChangeInterceptor, LoggerInterceptor loggerInterceptor) {
        this.dynamicContentLoader = dynamicContentLoader;
        this.loggerInterceptor = loggerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.dynamicContentLoader);
        registry.addInterceptor(loggerInterceptor).addPathPatterns("/wards/create");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:tmp/");
    }

    //Tomcat large file upload connection reset
    //http://www.mkyong.com/spring/spring-file-upload-and-connection-reset-issue/
    // Set maxPostSize of embedded tomcat server to 11 megabytes (default is 2 MB, not large enough to support file uploads > 1.5 MB)
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> getTomcatCustomizer() {
        return new WebServerFactoryCustomizer<TomcatServletWebServerFactory>() {
            @Override
            public void customize(TomcatServletWebServerFactory factory) {
                factory.addConnectorCustomizers(
                        (connector) -> {
                            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                                //-1 means unlimited
                                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(67108864); // 64 MB
                            }
                            connector.setMaxPostSize(67108864); // 64 MB
                        }
                );
            }
        };
    }
}
