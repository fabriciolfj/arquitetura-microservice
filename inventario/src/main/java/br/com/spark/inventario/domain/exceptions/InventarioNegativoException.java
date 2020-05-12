package br.com.spark.inventario.domain.exceptions;

public class InventarioNegativoException extends RuntimeException {

    public InventarioNegativoException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public InventarioNegativoException(final String msg) {
        super(msg);
    }
}
