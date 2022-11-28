package org.soulcodeacademy.helpr.services.errors;


// Está classe de erro quando uma operação está
// faltando dados... Ex: autualizar -> idFuncionario nulo
public class ParametrosInsuficientesError extends RuntimeException{

    public ParametrosInsuficientesError(String message){
        super(message);
    }
}
