package br.com.spark.produto.domain.core.integration.client;

import br.com.spark.produto.api.dto.InventarioDto;
import br.com.spark.produto.domain.core.integration.config.FeignConfiguration;
import br.com.spark.produto.domain.core.integration.fallback.InventarioFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventario",
            //url = "${inventario.url}", quanto esta fora do ambiente eureka
            decode404 = true,
            configuration = FeignConfiguration.class,
            fallbackFactory = InventarioFallbackFactory.class)
public interface InventarioClient {

    @GetMapping(value = "/inventario/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    InventarioDto getInventario(@PathVariable final String id);
}
