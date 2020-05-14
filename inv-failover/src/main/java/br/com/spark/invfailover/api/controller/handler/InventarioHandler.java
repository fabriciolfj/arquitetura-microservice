package br.com.spark.invfailover.api.controller.handler;


import br.com.spark.invfailover.api.dto.InventarioDto;
import br.com.spark.invfailover.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class InventarioHandler {

    private final InventarioService inventarioService;

    public Mono<ServerResponse> getInventario(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Mono
                        .just(inventarioService.buscarPorId()), InventarioDto.class);
    }
}
