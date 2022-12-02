package org.soulcodeacademy.empresa.services;


import org.soulcodeacademy.empresa.domain.Projeto;
import org.soulcodeacademy.empresa.domain.dto.ProjetoDTO;
import org.soulcodeacademy.empresa.repositories.ProjetoRepository;
import org.soulcodeacademy.empresa.services.error.RecursosNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private EnderecoService enderecoService;


    public List<Projeto> listar(){
        return this.projetoRepository.findAll();
    }

    public Projeto getProjeto(Integer idProjeto){
       return this.projetoRepository.findById(idProjeto)
               .orElseThrow(() -> new RecursosNaoEncontradoError("Projeto não encontrado!"));
    }

    public Projeto salvar(ProjetoDTO dto){
        Projeto projeto = new Projeto(null, dto.getNome(), dto.getOrcamento(), dto.getDescricao());
        return this.projetoRepository.save(projeto);
    }


    public Projeto atualizar(Integer idProjeto, ProjetoDTO dto){
        Projeto projetoAtual = this.getProjeto(idProjeto);

        projetoAtual.setNome(dto.getNome());
        projetoAtual.setOrcamento(dto.getOrcamento());
        projetoAtual.setDescricao(dto.getDescricao());

        return this.projetoRepository.save(projetoAtual);

    }

    public void deletar(Integer idProjeto){
        Projeto projeto = this.getProjeto(idProjeto);
        this.projetoRepository.delete(projeto);
    }

}
