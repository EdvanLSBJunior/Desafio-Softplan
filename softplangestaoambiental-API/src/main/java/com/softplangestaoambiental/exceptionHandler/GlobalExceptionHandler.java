package com.softplangestaoambiental.exceptionHandler;

import com.softplangestaoambiental.exceptions.ClienteNaoEncontradoException;
import com.softplangestaoambiental.exceptions.LimiteExcedidoException;
import com.softplangestaoambiental.exceptions.TransacaoInvalidaException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ErrorDetails> handleClienteNaoEncontradoException(ClienteNaoEncontradoException ex) {
        return createErrorResponse("Cliente não encontrado", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransacaoInvalidaException.class)
    public ResponseEntity<ErrorDetails> handleTransacaoInvalidaException(TransacaoInvalidaException ex) {
        return createErrorResponse("Tipo de transação invalida!", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(LimiteExcedidoException.class)
    public ResponseEntity<ErrorDetails> handleLimiteExcedidoException(LimiteExcedidoException ex) {
        return createErrorResponse("Limite excedido para a transação de debito.", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException ex) {
        return createErrorResponse("Tipo de transação inválida!", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Data
    @AllArgsConstructor
    public static class ErrorDetails {
        private String message;
        private HttpStatus httpStatus;
    }

    private ResponseEntity<ErrorDetails> createErrorResponse(String message, HttpStatus status) {
        ErrorDetails errorDetails = new ErrorDetails(message, status);
        return ResponseEntity.status(status).body(errorDetails);
    }
}
