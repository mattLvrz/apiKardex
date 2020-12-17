package com.pipecode.topshop.configuration;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.springmvc.configuration;
//
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Properties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.bind.annotation.RequestMethod;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.builders.ResponseMessageBuilder;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.ResponseMessage;
//import springfox.documentation.service.SecurityScheme;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger.web.UiConfiguration;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// *
// *
// */
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//    
////    @Bean
////    public Docket petApi() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .select()
////                .apis(RequestHandlerSelectors
////			.basePackage("com.springmvc.configuration.controller"))
////                .paths(PathSelectors.any())
////                .build().apiInfo(apiInfo())
////		.useDefaultResponseMessages(false)
////		.globalResponseMessage(RequestMethod.GET,globalResponseMessage())
////		.globalResponseMessage(RequestMethod.POST,globalResponseMessage())
////		.globalResponseMessage(RequestMethod.PUT,globalResponseMessage())
////		.globalResponseMessage(RequestMethod.DELETE,globalResponseMessage())
////		.produces(new HashSet<>(Arrays.asList("application/json")))
////		.securitySchemes(securitySchemes());
////    }
////    private ApiInfo apiInfo() {
////        Contact contacto = new Contact("Facundo Di Tullio", null, "facundo.ditullio@hospitalitaliano.org.ar");
////	return new ApiInfoBuilder()
////            .title("Sistema de Gestión de Encuentros")
////            .description("API de servicios REST.")
////            .contact(contacto)
////            .version(getVersion())
////            .build();
////    }
////    private String getVersion() {
////	    return "UNKNOWN";
////    }
////    private List<SecurityScheme> securitySchemes() {
////	List<SecurityScheme> list = new ArrayList<>();
////	list.add(new ApiKey("token", "Authorization", "header"));
////	return list;
////    }
////    
////    private List<ResponseMessage> globalResponseMessage() {
////	List<ResponseMessage> responseMessages = new ArrayList<>();
////	responseMessages.add(new ResponseMessageBuilder() 
////	    .code(401)
////	    .message("Unauthorized (Token invalido o inexistente)")
////	    .build());
////	responseMessages.add(new ResponseMessageBuilder() 
////	    .code(403)
////	    .message("Forbidden (El usuario no tiene permisos)")
////	    .build());
////	return responseMessages;
////    }
////    
////    @Bean
////    UiConfiguration uiConfig() {
////        return new UiConfiguration(
////                null, // url
////                "none", // docExpansion          => none | list
////                "alpha", // apiSorter             => alpha
////                "schema", // defaultModelRendering => schema
////                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
////                true, // enableJsonEditor      => true | false
////                false, // showRequestHeaders    => true | false
////                null);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
////    }
//}