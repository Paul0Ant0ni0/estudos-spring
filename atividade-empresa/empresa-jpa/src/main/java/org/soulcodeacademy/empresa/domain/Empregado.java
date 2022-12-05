package org.soulcodeacademy.empresa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Empregado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpregado;

    @Column(length = 120, nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private Double salario;

    @OneToOne // 1:1 (Empregado-Endereço)
    @JoinColumn(name = "id_endereco", nullable = false) // renomeia a coluna da FK
    private Endereco endereco;

    // Esta lista representa quais projetos o empregado participa
    @ManyToMany(fetch = FetchType.EAGER) // EAGER = antecipa os dados de projeto
    private Set<Projeto> projetos = new HashSet<>(); // Set é uma coleção de lista que não permite valores duplicados

    public Empregado() {
    }

    public Empregado(Integer idEmpregado, String nome, String email, Double salario) {
        this.idEmpregado = idEmpregado;
        this.nome = nome;
        this.email = email;
        this.salario = salario;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Set<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(Set<Projeto> projetos) {
        this.projetos = projetos;
    }
}
