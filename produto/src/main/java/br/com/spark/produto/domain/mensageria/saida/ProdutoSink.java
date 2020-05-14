package br.com.spark.produto.domain.mensageria.saida;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProdutoSink {

    String OUTPUT = "topic-produto";

    @Output(OUTPUT)
    MessageChannel produtoOutput();
}
