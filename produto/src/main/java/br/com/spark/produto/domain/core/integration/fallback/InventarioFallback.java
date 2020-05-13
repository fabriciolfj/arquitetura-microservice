package br.com.spark.produto.domain.core.integration.fallback;

import br.com.spark.produto.api.dto.InventarioDto;
import br.com.spark.produto.domain.core.integration.client.InventarioClient;

public class InventarioFallback implements InventarioClient {

    @Override
    public InventarioDto getInventario(String id) {
        var dto = new InventarioDto();
        dto.setQuantidade(999L);
        return dto;
    }
}
