package br.com.spark.produto.domain.mensageria;

import br.com.spark.produto.api.dto.InventarioDto;
import br.com.spark.produto.domain.mensageria.saida.ProdutoSink;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProdutoProducer {

    private final ProdutoSink sink;

    public void send(final InventarioDto dto) {
        log.info("Enviando novo produto para o inventario.");
        final Message<InventarioDto> message = MessageBuilder.withPayload(dto).build();
        sink.produtoOutput().send(message);
    }
}
