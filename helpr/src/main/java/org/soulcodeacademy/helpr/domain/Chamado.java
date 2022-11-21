package org.soulcodeacademy.helpr.domain;

import org.soulcodeacademy.helpr.domain.enums.StatusChamado;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idChamando;

    @Column(nullable = false, length = 120)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    private LocalDate dataAbertura = LocalDate.now(); // Incializando a data atual

    private LocalDate dataFechamento;

    @Enumerated(EnumType.STRING)
    private StatusChamado status = StatusChamado.RECEBIDO;

    @ManyToOne() // MUITOS CHAMANDOS P/ UM FUNCIONÁRIO -> Chaves estrageiras
    @JoinColumn(name = "id_funcionario") // FOREIGN KEI chamado(id_Funcionario) REFERENCES funcionario(id_Funcionario)
    private Funcionario funcionario;


    @ManyToOne() // MUTITOS CHAMADOS P/ UM CLIENTE -> Chaves estrageiras
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;



    public Chamado() {

    }

    public Chamado(Integer idChamando, String titulo, String descricao) {
        this.idChamando = idChamando;
        this.titulo = titulo;
        this.descricao = descricao;

    }

    public Integer getIdChamando() {
        return idChamando;
    }

    public void setIdChamando(Integer idChamando) {
        this.idChamando = idChamando;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
