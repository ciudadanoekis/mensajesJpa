package es.ua.jtech.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ConditionalOnProperty(value = "mensajes.api.rest.swagger.enabled", havingValue = "true")
@ComponentScan
public class SwaggerConfig {
	 private static final String API_KEY_NAME = "apiKeyParameter";
	    private static final String PASS_AS_HEADER = "header";

	    @Value("${mensajes.api.rest.version}")
	    private String apiVersion;

	    @Value("${mensajes.api.rest.swagger.groupName:'Nombre de Grupo'}")
	    private String groupName;

	    @Value("${mensajes.api.rest.swagger.apiTitle:'Título de API'}")
	    private String apiTitle;

	    @Value("${mensajes.api.rest.swagger.apiDescription:'Descripción de API'}")
	    private String apiDescription;

	    @Value("${mensajes.api.rest.swagger.enabled:false}")
	    private Boolean enabled;

	    @Bean
	    public Docket getApiDefinition() {
	        return new Docket(DocumentationType.SWAGGER_2)
	            .enable(enabled)
	            .groupName(groupName)
	            .useDefaultResponseMessages(false)
	            .apiInfo(apiInfo())
	            .select()
	            .apis(selectors())
	            .paths(paths())
	            .build()
	            .securitySchemes(Arrays.asList(apiKey()))
	            .securityContexts(Arrays.asList(securityContext()));
	    }

	    @Bean
	    public SecurityConfiguration security() {
	        return SecurityConfigurationBuilder
	            .builder()
	            .scopeSeparator(",")
	            .additionalQueryStringParams(null)
	            .useBasicAuthenticationWithAccessCodeGrant(Boolean.FALSE)
	            .build();
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	            .title(apiTitle)
	            .version(apiVersion)
	            .description(apiDescription)
	            .build();
	    }

	    /**
	     * This is necessary in order to filter the documented paths. If not
	     * spring-boot-actuator endpoints (and others) would be shown, when what we
	     * really want to document is the functional API.
	     */
	    private static java.util.function.Predicate<String> paths() {
	        return PathSelectors.any();
	    }

	    private static java.util.function.Predicate<RequestHandler> selectors() {
	        return RequestHandlerSelectors.withClassAnnotation(RestController.class);
	    }

	    private static ApiKey apiKey() {
	        return new ApiKey(API_KEY_NAME, HttpHeaders.AUTHORIZATION, PASS_AS_HEADER);
	    }

	    @SuppressWarnings("deprecation")
		private static SecurityContext securityContext() {
	        return SecurityContext
	            .builder()
	            .securityReferences(defaultAuth())
	            .forPaths(PathSelectors.any())
	            .build();
	    }

	    private static List<SecurityReference> defaultAuth() {
	        final AuthorizationScope authorizationScope = new AuthorizationScope("global", "Acceso Total");
	        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	        authorizationScopes[0] = authorizationScope;
	        return Arrays.asList(new SecurityReference(API_KEY_NAME, authorizationScopes));
	    }

}
