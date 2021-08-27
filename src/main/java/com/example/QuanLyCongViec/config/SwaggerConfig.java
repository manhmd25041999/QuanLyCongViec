package com.example.QuanLyCongViec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private ParameterBuilder generateParameterBuilder() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name("Authorization")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .description("Bearer + ${authentication token}")
                .required(false)
                .build();
        return parameterBuilder;
    }

    @Bean
    public Docket allApi() {
        ParameterBuilder parameterBuilder = generateParameterBuilder();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("ALL API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.QuanLyCongViec.rest"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(allApiInfo())
                .globalOperationParameters(Collections.singletonList(parameterBuilder.build()));

    }

    private ApiInfo allApiInfo() {
        return new ApiInfo(
                "All API",
                "All api",
                "",
                "",
                null,
                "License of API", "", Collections.emptyList());
    }
//
//    @Bean
//    public Docket apiAuthenticate() {
//        ParameterBuilder parameterBuilder = generateParameterBuilder();
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("Authenticate api")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.rabiloo.rest.auth"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(authenticateInfo())
//                .globalOperationParameters(Collections.singletonList(parameterBuilder.build()));
//    }
//
//    private ApiInfo authenticateInfo() {
//        return new ApiInfo(
//                "Authenticate API",
//                "Authenticate api only",
//                "",
//                "",
//                null,
//                "License of API", "", Collections.emptyList());
//    }
//
//    @Bean
//    public Docket apiWebCms() {
//        ParameterBuilder parameterBuilder = generateParameterBuilder();
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("Web cms api")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.rabiloo.rest.web"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(webCmsInfo())
//                .globalOperationParameters(Collections.singletonList(parameterBuilder.build()));
//    }
//
//    private ApiInfo webCmsInfo() {
//        return new ApiInfo(
//                "CMS Web API",
//                "Web cms api only",
//                "",
//                "",
//                null,
//                "License of API", "", Collections.emptyList());
//    }
//
//    @Bean
//    public Docket apiApp() {
//        ParameterBuilder parameterBuilder = generateParameterBuilder();
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("App api")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.rabiloo.rest.app"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(appInfo())
//                .globalOperationParameters(Collections.singletonList(parameterBuilder.build()));
//
//    }
//
//    private ApiInfo appInfo() {
//        return new ApiInfo(
//                "App API",
//                "App api only",
//                "",
//                "",
//                null,
//                "License of API", "", Collections.emptyList());
//    }
//
//    @Bean
//    public Docket apiStoreApp() {
//        ParameterBuilder parameterBuilder = generateParameterBuilder();
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("Store App api")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.rabiloo.rest.store_app"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(storeAppInfo())
//                .globalOperationParameters(Collections.singletonList(parameterBuilder.build()));
//
//    }
//
//    private ApiInfo storeAppInfo() {
//        return new ApiInfo(
//                "Store App API",
//                "Store App api only",
//                "",
//                "",
//                null,
//                "License of API", "", Collections.emptyList());
//    }
//
//    @Bean
//    public Docket apiImageServer() {
//        ParameterBuilder parameterBuilder = generateParameterBuilder();
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("Image Server api")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.rabiloo.rest.image"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(imageServerInfo())
//                .globalOperationParameters(Collections.singletonList(parameterBuilder.build()));
//
//    }
//
//    private ApiInfo imageServerInfo() {
//        return new ApiInfo(
//                "Image Server API",
//                "Image Server api only",
//                "",
//                "",
//                null,
//                "License of API", "", Collections.emptyList());
//    }
//
//
//    @Bean
//    public Docket apiDeveloper() {
//        ParameterBuilder parameterBuilder = generateParameterBuilder();
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("Developer api")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.rabiloo.rest.dev"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(imageServerInfo())
//                .globalOperationParameters(Collections.singletonList(parameterBuilder.build()));
//
//    }
//
//    private ApiInfo apiDeveloperInfo() {
//        return new ApiInfo(
//                "Developer API",
//                "Developer api only",
//                "",
//                "",
//                null,
//                "License of API", "", Collections.emptyList());
//    }
}
