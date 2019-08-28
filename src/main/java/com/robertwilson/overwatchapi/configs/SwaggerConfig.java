package com.robertwilson.overwatchapi.configs;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by: Robert Wilson
 * Date:  2019-08-28
 * Project: overwatch-api
 * Package: com.robertwilson.overwatchapi.configs
 * Class: SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket postsApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)

            .select()
            .apis(RequestHandlerSelectors.basePackage("com.robertwilson.overwatchapi"))
            .paths(paths())
            .build()
            .apiInfo(apiInfo());
    }

    private Predicate<String> paths()
    {
        return or(
            regex("/api/v1/hero.*"),
            regex("/api/v1/ability.*")
        );
    }

    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder()
            .title("Unofficial Overwatch API")
            .description("Unofficial Overwatch API reference for developers")
            .termsOfServiceUrl("https://github.com/ciriusrob/overwatch-api")
            .contact(new Contact("Robert Wilson", "https://github.com/ciriusrob", "developers@my-domain.com"))
            .license("Unofficial Overwatch License")
            .licenseUrl("developers@my-domain.com")
            .version("1.0").build();
    }
}
