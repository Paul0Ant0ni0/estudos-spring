package org.soulcodeacademy.helpr.controllers.errors;

// Quando acontece um erro, está classe decide como retornar
// a mensagem para o cliente

import org.soulcodeacademy.helpr.services.errors.ParametrosInsuficientesError;
import org.soulcodeacademy.helpr.services.errors.RecursosNaoEncontradoError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice // capacidade de gerenciar os erros que acontecem
public class ResourceExceptionHandler {

    @ExceptionHandler(RecursosNaoEncontradoError.class)
    public ResponseEntity<CustomErrorResponse> recursosNaoEncontradoError(RecursosNaoEncontradoError error, HttpServletRequest request){
        CustomErrorResponse response = new CustomErrorResponse();

        response.setTimestamp(LocalDateTime.now()); // Define a data e hora que o erro aconteceu
        response.setStatus(HttpStatus.NOT_FOUND.value()); // Define como 404 o código status
        response.setMessage(error.getMessage()); // Define a mensagem de erro vinda do servidor
        response.setPath(request.getRequestURI()); // Define qual endpoint ocorreu a requisição

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

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<CustomErrorResponse> badCredentialsException(BadCredentialsException error, HttpServletRequest request){
        CustomErrorResponse response = new CustomErrorResponse();

        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.FORBIDDEN.value()); //403
        response.setMessage("E-mail/senha inválidos!");
        response.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

}
