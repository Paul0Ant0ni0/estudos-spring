package org.soulcodeacademy.helpr.domain.dto;

import org.soulcodeacademy.helpr.domain.Chamado;
import org.soulcodeacademy.helpr.domain.enums.StatusChamado;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChamadoDTO extends Chamado {
    @NotBlank(message = "Campo título é obrigatório")
    private String titulo;

    @NotBlank(message = "Campo descrição é obrigatório")
    private String descricao;

    @NotNull(message = "Campo idCliente é obrigatório")
    private Integer idCliente;

    @NotNull(message = "Campo Status é obrigatório") // Evitando que o status seja nulo
    private StatusChamado status;

    private Integer idFuncionario;

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public StatusChamado getStatus() {
        return status;
    }

    @Override
    public void setStatus(StatusChamado status) {
        this.status = status;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) { this.idFuncionario = idFuncionario; }
}
