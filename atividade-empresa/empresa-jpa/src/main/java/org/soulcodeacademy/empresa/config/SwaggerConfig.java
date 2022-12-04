package org.soulcodeacademy.empresa.config;


import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;

@Configuration // classe de configuração para o spring
@EnableSwagger2 // habilitando a configuração para a documentação para do swagger
public class SwaggerConfig {

    private Contact contato() {
        return new Contact(
                "Paulo Antonio",
                "http://www.seusite.com.br",
                "pauloantonio785@gmail.com");
    }

    private ApiInfoBuilder informacoesApi() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("Rest API: Empresa");
        apiInfoBuilder.description("API para práticar os conteúdos passados em sala de aula");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.termsOfServiceUrl("Termo de uso: Open Source");
        apiInfoBuilder.license("Licença - Sua Empresa");
        apiInfoBuilder.licenseUrl("http://www.seusite.com.br");
        apiInfoBuilder.contact(this.contato());

        return apiInfoBuilder;

    }

    @Bean // Documento em forma de bean para que o spring crie a documentaçõa da api
    public Docket detalheApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        docket
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.soulcodeacademy.empresa.controllers")) // passando o pacote com os controllers
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.informacoesApi().build())
                .consumes(new HashSet<String>(Arrays.asList("application/json")))
                .produces(new HashSet<String>(Arrays.asList("application/json")));

        return docket;
    }
}
