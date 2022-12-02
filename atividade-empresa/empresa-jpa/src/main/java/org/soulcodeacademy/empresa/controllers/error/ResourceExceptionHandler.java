package org.soulcodeacademy.empresa.controllers.error;

import org.soulcodeacademy.empresa.services.error.ParametrosInsuficientesError;
import org.soulcodeacademy.empresa.services.error.RecursosNaoEncontradoError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(RecursosNaoEncontradoError.class)
    public ResponseEntity<CustomErrorResponse> recursosNaoEncontradoError(RecursosNaoEncontradoError error, HttpServletRequest request){
        CustomErrorResponse response = new CustomErrorResponse();

        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(error.getMessage());
        response.setPath(request.getRequestURI());

        // Retorna o objeto com os dados e código 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ParametrosInsuficientesError.class)
    public ResponseEntity<CustomErrorResponse> parametrosInsuficientesError(ParametrosInsuficientesError error, HttpServletRequest request){
        CustomErrorResponse response = new CustomErrorResponse();

        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(error.getMessage());
        response.setPath(request.getRequestURI());

        // Retorna o objeto com os dados e código 400
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
