package br.com.apidoacao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${api.info.title}")
    private String tituloAplicacao;

    @Value("${api.info.description}")
    private String descricaoAplicacao;

    @Value("${api.info.version}")
    private String versaoAplicacao;

    @Value("${api.info.contact.name}")
    private String nomeEngenheiro;

    @Value("${api.info.contact.url}")
    private String urlAplicacao;

    @Value("${api.info.contact.email}")
    private String emailEngenheiro;

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.apidoacao.entrypoint"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(apiKey()))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title(tituloAplicacao)
                .description(descricaoAplicacao)
                .version(versaoAplicacao)
                .contact(contact())
                .build();
    }

    private Contact contact() {

        return new Contact(
                nomeEngenheiro,
                urlAplicacao,
                emailEngenheiro);
    }

    public ApiKey apiKey() {

        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {

        return SecurityContext
                .builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {

        AuthorizationScope authorizationScope = new AuthorizationScope("global", "acessEverything");
        AuthorizationScope[] scopes = new AuthorizationScope[1];

        scopes[0] = authorizationScope;

        SecurityReference reference = new SecurityReference("JWT", scopes);

        return List.of(reference);
    }

}