package br.com.spark.produto.domain.core.integration.config;


import br.com.spark.produto.api.exception.decoder.InventarioClientErrorDecorder;
import br.com.spark.produto.domain.core.integration.client.InventarioClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FeignConfiguration {

    @Bean
    public InventarioClientErrorDecorder inventarioClientErrorDecorder() {
        return new InventarioClientErrorDecorder();
    }
}
