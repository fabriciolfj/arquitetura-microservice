package br.com.spark.inventario.domain.exceptions;

public class InventarioNaoLocalizadoException extends RuntimeException {

    public InventarioNaoLocalizadoException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public InventarioNaoLocalizadoException(final String msg) {
        super(msg);
    }
}
