package br.com.spark.inventario.domain.mensageria.input;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InventarioSink {

    String INPUT = "input-produto";

    @Input(INPUT)
    SubscribableChannel inventarioInput();
}
