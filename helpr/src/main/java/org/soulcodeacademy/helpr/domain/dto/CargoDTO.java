package org.soulcodeacademy.helpr.domain.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

// DTO -> Objeto de transferencia de dados.
// É util para validarmos as informações transferidas pelo cliente
public class CargoDTO {
    //DATA TRANSFER OBJECT

    //Impede que o nome seja "", por exemplo.
    //Message é o texto de validação
    @NotBlank(message = "Campo nome é obrigatório")
    private String nome;
    @NotBlank(message = "Campo descrição é obrigatório")
    private String descricao;
    // Impede que o valor seja null

    @NotNull(message = "Campo salário é obrigatório")
    @Min(value = 500, message = "Campo salário inválido")
    @Max(value = 100000, message = "Campo salário inválido")
    private Double salario;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}
