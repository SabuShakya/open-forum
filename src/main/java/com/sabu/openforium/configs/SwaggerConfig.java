package com.sabu.openforium.configs;

import com.google.common.base.Predicate;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    Predicate<RequestHandler> predicate = (RequestHandler input) -> {
        Class<?> declaringClass = input.declaringClass();
        if (declaringClass.getPackage().getName().startsWith("com.sabu")) {
            if (declaringClass == BasicErrorController.class) {
                return false;
            }
            if (declaringClass.isAnnotationPresent(RestController.class)) {
                return true;
            }
            if (input.isAnnotatedWith(ResponseBody.class)) {
                return true;
            }
        }
        return false;
    };

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Open Forum Server Web Panel",
                "Open Forum Server Web API management",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("F1soft", "http://sabushakya.com.np/",
                        "sabushakya1@gmail.com"),
                "API License",
                "http://www.test.com/",
                Collections.emptyList());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(predicate)
                .paths(PathSelectors.any())
                .build();
    }
}
