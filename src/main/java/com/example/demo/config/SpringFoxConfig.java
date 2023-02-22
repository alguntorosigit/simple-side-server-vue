package com.example.demo.config;

import com.example.demo.constant.AppConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    private ApiInfo apiInfo(){
        return new ApiInfo("demo",
            "User",
            "1.0",
            "Terms of service",
            new Contact("Al Sigit Guntoro", "https://github.com/alguntorosigit", "alsigitguntoro@gmail.com"),
                "License of API",
                "API lincense URL",
                Collections.emptyList());
    }

    @Bean
    public Docket apiDocumentation(){
        return new Docket((DocumentationType.SWAGGER_2))
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

    private ApiKey apiKey(){
        return new ApiKey("JWT", AppConstant.JWT_HEADER, "header");
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}
