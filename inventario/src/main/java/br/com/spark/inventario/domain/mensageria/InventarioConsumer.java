package br.com.spark.inventario.domain.mensageria;

import br.com.spark.inventario.api.dto.InventarioDto;
import br.com.spark.inventario.api.mapper.InventarioMapper;
import br.com.spark.inventario.domain.mensageria.input.InventarioSink;
import br.com.spark.inventario.domain.service.InventarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableBinding(InventarioSink.class)
public class InventarioConsumer {

    private final InventarioMapper mapper;
    private final InventarioService inventarioService;

    @StreamListener(InventarioSink.INPUT)
    public void onMessage(@Payload final InventarioDto inventarioDto) {
        log.info("Criando inventario do produto: " + inventarioDto.getCodigoProduto());
        inventarioService.salvar(mapper.toDomain(inventarioDto));
    }
}
