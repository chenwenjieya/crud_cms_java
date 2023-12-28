package com.cj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 访问官方文档 UI界面：http://127.0.0.1:18080/swagger-ui.html
     * <p>
     * 增强版 UI界面：http://127.0.0.1:18080/doc.html
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2) // SWAGGER_2
                .apiInfo(apiInfo())
                .select()
                // 此处自行修改为自己的 Controller 包路径
                .apis(RequestHandlerSelectors.basePackage("com.cj.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(setGlobalParameters());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("CRUD 项目接口文挡")
                .description("本文档描述CRUD项目接口定义")
                .version("1.0")
                .termsOfServiceUrl("") //文档生成的主页地址
                .contact(new Contact("author", null, "xxxx@163.com"))
                .build();
    }

    /**
     * 设置全局参数
     *
     * @return
     */
    private List<Parameter> setGlobalParameters() {
        List<Parameter> globalParameterList = new ArrayList<>();

        //Header中必需 token参数。非必填，传空也可以，一般业务登录拦截器校验 token是否合法
        ParameterBuilder tokenBuilder = new ParameterBuilder();
        tokenBuilder.name("token").description("用户 TOKEN参数")
                .required(false)// 非必填
                .modelRef(new ModelRef("string"))
                .parameterType("header");
        globalParameterList.add(tokenBuilder.build());

        return globalParameterList;
    }


}
