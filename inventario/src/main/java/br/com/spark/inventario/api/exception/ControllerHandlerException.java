package br.com.spark.inventario.api.exception;

import br.com.spark.inventario.domain.exceptions.InventarioNaoLocalizadoException;
import br.com.spark.inventario.domain.exceptions.InventarioNegativoException;
import br.com.spark.inventario.domain.exceptions.TipoNaoEncontradoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ControllerHandlerException extends ResponseEntityExceptionHandler {

    public static final String ERRO_INTERNO_INESPERADO = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entrar em contato com o adminstrador do sistema";

    @ExceptionHandler(InventarioNegativoException.class)
    public ResponseEntity<?> handleInventarioNegativoException(final InventarioNegativoException e, final WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var problema = createProblemBuilder(status, e.getMessage()).build();

        return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(InventarioNaoLocalizadoException.class)
    public ResponseEntity<?> handleInventarioNaoLocalizadoException(final InventarioNaoLocalizadoException e, final WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var problema = createProblemBuilder(status, e.getMessage()).build();

        return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(TipoNaoEncontradoException.class)
    public ResponseEntity<?> handleTipoNaoEncontradoException(final TipoNaoEncontradoException e, final WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var problema = createProblemBuilder(status, e.getMessage()).build();

        return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = Problema.builder()
                    .timestamp(OffsetDateTime.now())
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .userMessage(ERRO_INTERNO_INESPERADO)
                    .build();
        } else if (body instanceof String) {
            body = Problema.builder()
                    .timestamp(OffsetDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .userMessage(ERRO_INTERNO_INESPERADO)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problema.ProblemaBuilder createProblemBuilder(HttpStatus status, String detail){
        return Problema.builder()
                .title(status.getReasonPhrase())
                .detail(detail)
                .status(status.value())
                .timestamp(OffsetDateTime.now());
    }


}
