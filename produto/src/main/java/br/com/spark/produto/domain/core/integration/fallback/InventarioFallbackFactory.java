package br.com.spark.produto.domain.core.integration.fallback;

import br.com.spark.produto.domain.core.integration.client.InventarioClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class InventarioFallbackFactory implements FallbackFactory<InventarioClient> {
    @Override
    public InventarioClient create(Throwable cause) {
        return new InventarioFallback();
    }
}
