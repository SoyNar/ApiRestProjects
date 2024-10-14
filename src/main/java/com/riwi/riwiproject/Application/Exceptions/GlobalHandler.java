package com.riwi.riwiproject.Application.Exceptions;

import com.riwi.riwiproject.domain.Excepcions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponses> handleException(Exception ex) {
        // Crear el objeto ErrorResponses con todos los campos necesarios
        ErrorResponses errorResponse = new ErrorResponses(
                ex.getMessage(),  // Mensaje de la excepción
                HttpStatus.INTERNAL_SERVER_ERROR.value(),  // Código de estado HTTP
                LocalDateTime.now(),  // Timestamp del error
                "Un error inesperado ha ocurrido."  // Detalles adicionales (puedes modificarlo)
        );

        // Retornar la respuesta con el código de estado 500 (INTERNAL_SERVER_ERROR)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    // Manejar excepciones de tipo ProyectNotFoundException
    @ExceptionHandler(ProyectNoFoundException.class)
    public ResponseEntity<ErrorResponses> handleProyectNotFoundException(ProyectNoFoundException ex, WebRequest request) {
        // Crear el objeto ErrorResponses con todos los campos necesarios
        ErrorResponses errorResponse = new ErrorResponses(
                ex.getMessage(),  // Mensaje de la excepción
                HttpStatus.NOT_FOUND.value(),  // Código de estado HTTP
                LocalDateTime.now(),  // Timestamp del error
                request.getDescription(false)  // Detalles, como la URI del request
        );

        // Retornar la respuesta con el código de estado 404 (NOT_FOUND)
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponses> handleIllegalArgumentException(IllegalArgumentException ex) {
        // Crear el objeto ErrorResponses con todos los campos necesarios
        ErrorResponses errorResponses = new ErrorResponses(
                ex.getMessage(),  // Mensaje de la excepción
                HttpStatus.BAD_REQUEST.value(),  // Código de estado HTTP
                LocalDateTime.now(),  // Timestamp del error
                "El argumento proporcionado es inválido."  // Detalles adicionales
        );

        // Retornar la respuesta con el código de estado 400 (BAD_REQUEST)
        return ResponseEntity.badRequest().body(errorResponses);
    }


    // Manejar excepciones de tipo ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponses> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        // Crear el objeto ErrorResponses con todos los campos necesarios
        ErrorResponses errorResponse = new ErrorResponses(
                ex.getMessage(),  // Mensaje de la excepción
                HttpStatus.NOT_FOUND.value(),  // Código de estado HTTP
                LocalDateTime.now(),  // Timestamp del error
                request.getDescription(false)  // Detalles, como la URI del request
        );

        // Retornar la respuesta con el código de estado 404 (NOT_FOUND)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
