package br.com.spark.produto;

import br.com.spark.produto.domain.core.integration.client.InventarioClient;
import br.com.spark.produto.domain.mensageria.saida.ProdutoSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(ProdutoSink.class)
@EnableCircuitBreaker
@SpringBootApplication
@EnableFeignClients(basePackageClasses = InventarioClient.class)
public class ProdutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutoApplication.class, args);
	}

}
