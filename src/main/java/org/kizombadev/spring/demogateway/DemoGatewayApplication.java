package org.kizombadev.spring.demogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/get")
						.filters(f -> f
								.rewritePath("get", "demo-service/v1/demo")
								.addRequestHeader("Hello", "World")
								.addRequestHeader("Authorization", "Basic dXNlcjpwYXNzd29yZA=="))
						.uri("http://localhost:8080"))
				.build();
	}

}
