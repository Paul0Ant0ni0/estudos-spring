package org.soulcodeacademy.empresa.domain.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProjetoDTO {


    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    @NotNull(message = "O campo orçamento é obrigatório")
    private Double orcamento;

    @NotBlank(message = "O campo descrição é obrigatório")
    private String descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Double orcamento) {
        this.orcamento = orcamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
