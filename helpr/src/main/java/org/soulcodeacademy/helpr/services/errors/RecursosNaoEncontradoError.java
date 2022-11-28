package org.soulcodeacademy.helpr.services.errors;

// Esta classe representa o erro de regra de negócio
// quando não encontramos cargos, clientes, funcionarios, chamados
// no Banco
public class RecursosNaoEncontradoError extends RuntimeException{

    public RecursosNaoEncontradoError(String message){
        super(message); // Passamos a mensagem para o RuntimeException
    }

}
