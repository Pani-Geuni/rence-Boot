package com.rence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SuppressWarnings("unchecked")
public class SwaggerConfig extends WebMvcConfigurationSupport {

	// API마다 구분짓기 위한 설정.
	@Bean
	public Docket allApi() {
		return getDocket("전체", Predicates.or(PathSelectors.regex("/*.*")));
	}
	
	@Bean
	public Docket commonApi() {
		return getDocket("공통", Predicates.or(PathSelectors.regex("/common.*")));

	}
	@Bean
	public Docket userApi() {
		return getDocket("유저", Predicates.or(PathSelectors.regex("/rence.*")));
	}
	
	@Bean
	public Docket officeApi() {
		return getDocket("오피스", Predicates.or(PathSelectors.regex("/office.*")));
	}

	@Bean
	public Docket backofficeApi() {
		return getDocket("백오피스", Predicates.or(PathSelectors.regex("/backoffice.*")));
	}

	@Bean
	public Docket searchApi() {
		return getDocket("마스터", Predicates.or(PathSelectors.regex("/master.*")));
	}

	

	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	// swagger 설정.
	public Docket getDocket(String groupName, Predicate<String> predicate) {
		return new Docket(DocumentationType.SWAGGER_2).groupName(groupName)
//               .consumes(getConsumeContentTypes())  //Parameter content type 설정
				.apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.rence"))
				.paths(predicate).apis(RequestHandlerSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("RENCE").description("공간 공유 플랫폼").version("0.0.1").build();
	}

	@Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().displayRequestDuration(true).validatorUrl("").build();
	}
}
