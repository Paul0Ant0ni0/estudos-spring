package org.soulcodeacademy.empresa.services.error;

public class RecursosNaoEncontradoError extends RuntimeException{
    public RecursosNaoEncontradoError(String message){
        super(message);
    }
}
