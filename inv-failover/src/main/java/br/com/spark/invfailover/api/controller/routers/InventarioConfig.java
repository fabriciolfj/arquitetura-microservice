package br.com.spark.invfailover.api.controller.routers;


import br.com.spark.invfailover.api.controller.handler.InventarioHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class InventarioConfig {

    @Bean
    public RouterFunction inventarioRoute(InventarioHandler handler) {
        return route(GET("/inventario-failover").and(accept(MediaType.APPLICATION_JSON)), handler::getInventario);
    }
}
