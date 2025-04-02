package com.jhops10.agenda_barbearia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<Object> handleClienteNotFoundException(ClienteNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Erro! Cliente não Encontrado!", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(BarbeiroNotFoundException.class)
    public ResponseEntity<Object> handleBarbeiroNotFoundException(BarbeiroNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Erro! Barbeiro não Encontrado!", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ServicoNotFoundException.class)
    public ResponseEntity<Object> handleServicoNotFoundException(ServicoNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Erro! Serviço não Encontrado!", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
