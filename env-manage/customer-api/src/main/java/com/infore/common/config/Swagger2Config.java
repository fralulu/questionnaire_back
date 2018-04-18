package com.infore.common.config;

import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by xuyao on 2017/7/6.
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("E-User-Token").description("认证token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("develop")
            .apiInfo(apiInfo())
            .select()
//            .apis(RequestHandlerSelectors.basePackage("com.infore.controller"))
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))//扫有注解的api
            .paths(PathSelectors.any())
            .build()
            .globalOperationParameters(pars)
            ;
    }
//http://127.0.0.1:8089/envapi/swagger-ui.html
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("问卷管家综合应用平台")
            .description("api 列表")
            .termsOfServiceUrl("localhost:8099")
            .version("1.0")
            .build();
    }

}
