package org.soulcodeacademy.empresa.services;


import org.soulcodeacademy.empresa.domain.Dependente;
import org.soulcodeacademy.empresa.domain.Empregado;
import org.soulcodeacademy.empresa.domain.dto.DependenteDTO;
import org.soulcodeacademy.empresa.repositories.DependenteRepository;
import org.soulcodeacademy.empresa.services.error.RecursosNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DependenteService {

    @Autowired
    private DependenteRepository dependenteRepository;

    @Autowired
    private EmpregadoService empregadoService;


    public List<Dependente> listar(){
        return this.dependenteRepository.findAll();
    }

    public Dependente getDependente(Integer idDepedente){
        return this.dependenteRepository.findById(idDepedente)
                .orElseThrow(() -> new RecursosNaoEncontradoError("Dependente não encontrado"));
    }

    public Dependente salvar(DependenteDTO dto){
        Dependente dependente = new Dependente(null, dto.getNome(), dto.getIdade());
        Empregado empregado = this.empregadoService.getEmpregado(dto.getIdResponsavel());
        dependente.setResponsavel(empregado);


        return this.dependenteRepository.save(dependente);
    }

    public Dependente atualizar(Integer idDependente, DependenteDTO dto){
        Dependente dependenteAtual = this.getDependente(idDependente);
        Empregado empregado = this.empregadoService.getEmpregado(dto.getIdResponsavel());
        dependenteAtual.setResponsavel(empregado);

        dependenteAtual.setNome(dto.getNome());
        dependenteAtual.setIdade(dto.getIdade());

        return this.dependenteRepository.save(dependenteAtual);
    }

    public void deletar(Integer idDependente){
        Dependente dependente = this.getDependente(idDependente);

        this.dependenteRepository.delete(dependente);
    }








}
