package com.example.demoOpinity.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	// this determines whether we will send along our apiInfo and which parts of our
	// API are exposed by swagger. For simplicity we're just showing everything as
	// indicated by the any() methods used by the rerequestHandlerSelectors and
	// PathSelectors
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	// the info swagger will show for our API
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Opinity Excel API").description("Opinity Excel API made by Stijn de Leeuw").build();
	}

}