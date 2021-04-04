package com.csx.csxspringboot.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SwaggerConfig
 * @Description: TODO
 * @author: Cuisx
 * @date: 2021/3/27 19:56
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //api接口包扫描路径
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.csx.csxspringboot";
    public static final String VERSION = "1.0.0";

    /**
     * select() 函数返回一个 ApiSelectorBuilder实例用来控制哪些接口暴露给 Swagger 来展现，
     * 本例采用指定扫描的包路径来定义，Swagger 会扫描该包下所有 Controller 定义的 API，并产生文档内容（除了被 @ApiIgnore
     * 指定的请求）。
     *
     * @Author Cuisx
     * @Date 20:12 2021/3/27
     * @param
     * @return springfox.documentation.spring.web.plugins.Docket
     */
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("csx-swagger-test")
                .apiInfo(webApiInfo()) //apiInfo用来创建该 Api 的基本信息
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
//                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
//                .paths(Predicates.not(PathSelectors.regex("/error.*"))) // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("网站-spring-boot项目调试")//设置文档的标题
                .description("本文档描述了测试项目中接口定义")// 设置文档的描述
                .version(VERSION)// 设置文档的版本信息
//                .contact(new Contact("Cuisx","http://csx.test.com","csx@csx.com"))
                .termsOfServiceUrl("http://csx.test.com")// 设置文档的License信息
                .build();
    }

}
