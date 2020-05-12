package br.com.spark.produto.domain.exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException(final String msg) {
        super(msg);
    }

    public ProdutoNaoEncontradoException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}
