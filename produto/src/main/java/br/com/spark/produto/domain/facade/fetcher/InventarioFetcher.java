package br.com.spark.produto.domain.facade.fetcher;

import br.com.spark.produto.api.dto.InventarioDto;
import br.com.spark.produto.domain.core.integration.client.InventarioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InventarioFetcher {

    private final InventarioClient inventarioClient;

    public InventarioDto getInventario(final String codeProduto) {
        log.info("Realizado busca inventario do produto: " + codeProduto);
        return inventarioClient.getInventario(codeProduto);
    }
}
