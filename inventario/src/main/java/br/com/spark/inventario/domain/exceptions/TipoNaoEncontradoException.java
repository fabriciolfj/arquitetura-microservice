package br.com.spark.inventario.domain.exceptions;

public class TipoNaoEncontradoException extends RuntimeException {

    public TipoNaoEncontradoException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public TipoNaoEncontradoException(final String msg) {
        super(msg);
    }
}
