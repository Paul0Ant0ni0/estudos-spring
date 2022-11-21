package org.soulcodeacademy.helpr.domain.dto;

import org.soulcodeacademy.helpr.domain.Chamado;
import org.soulcodeacademy.helpr.domain.enums.StatusChamado;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//Verifica os dados inseridos de cliente
public class ClienteDTO extends UsuarioDTO{

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @NotNull(message = "Campo chamado é obrigatório")
    private Chamado chamado;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Chamado getChamado() {
        return chamado;
    }

    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
    }
}

// @NotNull => só verifica se tem valor ou não (objetos, integer, double)
// @NotBlank => verifica se o telefone está "" (string)