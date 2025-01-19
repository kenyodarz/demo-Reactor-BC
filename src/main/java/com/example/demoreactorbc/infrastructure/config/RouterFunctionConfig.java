package com.example.demoreactorbc.infrastructure.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.example.demoreactorbc.infrastructure.web.in.handler.ProcessHandler;
import com.example.demoreactorbc.infrastructure.web.in.handler.RoleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterFunctionConfig {

  @Bean
  public RouterFunction<ServerResponse> routes(RoleHandler roleHandler, ProcessHandler processHandler) {

    return route(GET("/api/v1/roles"), roleHandler::getAll)
        .andRoute(GET("/api/v1/roles/{id}"), roleHandler::find)
        .andRoute(POST("/api/v1/roles"), roleHandler::save)
//        .andRoute(PUT("/api/v1/roles/{id}"), roleHandler::editar)
        .andRoute(DELETE("/api/v1/roles/{id}"), roleHandler::delete)
        // Rutas para ProcessHandler
        .andRoute(GET("/api/v1/processes"), processHandler::getAll)
        .andRoute(GET("/api/v1/processes/{id}"), processHandler::find)
        .andRoute(POST("/api/v1/processes"), processHandler::save)
//        .andRoute(PUT("/api/v1/processes/{id}"), processHandler::editar)
        .andRoute(DELETE("/api/v1/processes/{id}"), processHandler::delete);
  }
}

