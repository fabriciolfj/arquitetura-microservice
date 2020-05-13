package br.com.spark.produto.api.exception.decoder;

import br.com.spark.produto.api.exception.InventarioServiceException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class InventarioClientErrorDecorder implements ErrorDecoder {

    private ErrorDecoder delegate = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        final var statusCode = HttpStatus.valueOf(response.status());

        if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR) {
            throw new InventarioServiceException("Falha ao consumir o servico");
        }

        return delegate.decode(methodKey, response);
    }
}
