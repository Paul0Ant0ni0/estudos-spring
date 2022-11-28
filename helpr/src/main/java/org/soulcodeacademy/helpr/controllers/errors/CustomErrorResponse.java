package org.soulcodeacademy.helpr.controllers.errors;

import java.time.LocalDateTime;

// Está classe customiza a resposta de erro original do spring
public class CustomErrorResponse {

    private String message; // Mensagem explicando o erro
    private Integer status; // Código de Status Http
    private LocalDateTime timestamp; // Registro da data e hora em que o erro acontece
    private String path; // Endpoint em que o erro ocorre


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
