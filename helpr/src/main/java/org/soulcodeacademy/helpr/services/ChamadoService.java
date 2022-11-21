package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Chamado;
import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.dto.ChamadoDTO;
import org.soulcodeacademy.helpr.domain.enums.StatusChamado;
import org.soulcodeacademy.helpr.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ClienteService clienteService;

    public List<Chamado> listarChamado(){
        return this.chamadoRepository.findAll();
    }

    public Chamado getChamado(Integer idChamado){
        // Caso não encontre o chamado, lança a exceção.
        return this.chamadoRepository.findById(idChamado)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));
    }
    // Prática I: montar o salvar
    public Chamado salvar(ChamadoDTO dto){
        //Verificar se existe um cliente com ID
        Cliente cliente = this.clienteService.getCliente(dto.getIdCliente());

        Chamado chamado = new Chamado(null, dto.getTitulo(), dto.getDescricao());
        chamado.setCliente(cliente); // associa o cliente ao chamado

        return this.chamadoRepository.save(chamado);
    }

    // Prática II: montar o atualizar
    public Chamado atualizarChamado(Integer idChamado, ChamadoDTO dto){
        Chamado chamadoAtual = this.getChamado(idChamado);
        Cliente cliente = this.clienteService.getCliente(dto.getIdCliente());
        chamadoAtual.setTitulo(dto.getTitulo());
        chamadoAtual.setDescricao(dto.getDescricao());
        chamadoAtual.setCliente(cliente);

        if (dto.getIdFuncionario() == null){
            throw new RuntimeException("idFuncionário é obrigatório");
        }else{
            Funcionario funcionario = this.funcionarioService.getFuncionario(dto.getIdFuncionario());

            switch (dto.getStatus()){
                case RECEBIDO -> {
                    chamadoAtual.setStatus(StatusChamado.RECEBIDO);
                    chamadoAtual.setFuncionario(null);
                    chamadoAtual.setDataFechamento(null);
                }
                case ATRIBUIDO -> {
                    chamadoAtual.setStatus(StatusChamado.ATRIBUIDO);
                    chamadoAtual.setFuncionario(funcionario);
                    chamadoAtual.setDataFechamento(null);
                }
                case CONCLUIDO -> {
                    chamadoAtual.setStatus(StatusChamado.CONCLUIDO);
                    chamadoAtual.setFuncionario(funcionario);
                    chamadoAtual.setDataFechamento(LocalDate.now());

                }

            }
        }


        return this.chamadoRepository.save(chamadoAtual);
    }


    public List<Chamado> listarPorStatus(StatusChamado status){
        return this.chamadoRepository.findByStatus(status);
    }

    public List<Chamado> listarPorFuncionario(Integer idFuncionario){
        Funcionario funcionario = this.funcionarioService.getFuncionario(idFuncionario);

        return this.chamadoRepository.findByFuncionario(funcionario);
    }

    public List<Chamado> listarPorCliente(Integer idCliente){
        Cliente cliente = this.clienteService.getCliente(idCliente);

        return this.chamadoRepository.findByCliente(cliente);
    }

    public List<Chamado> listarPorIntervalosDatas(LocalDate data1, LocalDate data2){
        return this.chamadoRepository.buscarEntreData(data1, data2);
    }
}



