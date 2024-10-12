package com.learn.microservices.gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

	@Bean
	public RouterFunction<ServerResponse> productServiceRoute() {
		return GatewayRouterFunctions.route("product_service")
				.route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:8080"))
				.route(RequestPredicates.path("/api/product/{id}"), HandlerFunctions.http("http://localhost:8080"))
				.route(RequestPredicates.path("/api/product/update/{id}"),
						HandlerFunctions.http("http://localhost:8080"))
				.route(RequestPredicates.path("/api/product/remove/{id}"),
						HandlerFunctions.http("http://localhost:8080"))
				.build();
	}

	@Bean
	public RouterFunction<ServerResponse> orderServiceRoute() {
		return GatewayRouterFunctions.route("order_service")
				.route(RequestPredicates.path("/api/order"), HandlerFunctions.http("http://localhost:8081"))
				.route(RequestPredicates.path("/api/order/{id}"), HandlerFunctions.http("http://localhost:8081"))
				.route(RequestPredicates.path("/api/order/update/{id}"), HandlerFunctions.http("http://localhost:8081"))
				.route(RequestPredicates.path("/api/order/remove/{id}"), HandlerFunctions.http("http://localhost:8081"))
				.build();
	}

	@Bean
	public RouterFunction<ServerResponse> inventoryServiceRoute() {
		return GatewayRouterFunctions.route("inventory_service")
				.route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http("http://localhost:8082"))
				.route(RequestPredicates.path("/api/inventory/all"), HandlerFunctions.http("http://localhost:8082"))
				.route(RequestPredicates.path("/api/inventory/item/{id}"),
						HandlerFunctions.http("http://localhost:8082"))
				.route(RequestPredicates.path("/api/inventory/update/{id}"),
						HandlerFunctions.http("http://localhost:8082"))
				.route(RequestPredicates.path("/api/inventory/remove/{id}"),
						HandlerFunctions.http("http://localhost:8082"))
				.build();
	}
}
