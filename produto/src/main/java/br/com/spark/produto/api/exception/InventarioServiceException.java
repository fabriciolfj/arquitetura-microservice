package br.com.spark.produto.api.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InventarioServiceException extends RuntimeException {

    public InventarioServiceException(final String msg) {
        super(msg);
    }
}
