package org.soulcodeacademy.empresa.services;

import org.soulcodeacademy.empresa.domain.Endereco;
import org.soulcodeacademy.empresa.domain.dto.EnderecoDTO;
import org.soulcodeacademy.empresa.repositories.EnderecoRepository;
import org.soulcodeacademy.empresa.services.error.RecursosNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;


    public List<Endereco> listar(){
        return this.enderecoRepository.findAll();
    }

    public Endereco getEndereco(Integer idEndereco){
        return this.enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new RecursosNaoEncontradoError("Endereco não encontrado"));
    }

    public Endereco salvar(EnderecoDTO dto){
        Endereco endereco = new Endereco(null, dto.getCidade(), dto.getUf());
        return this.enderecoRepository.save(endereco);
    }

    public Endereco atualizar(Integer idEndereco, EnderecoDTO dto) {
        Endereco enderecoAtual = this.getEndereco(idEndereco);
        enderecoAtual.setCidade(dto.getCidade());
        enderecoAtual.setUf(dto.getUf());

        return this.enderecoRepository.save(enderecoAtual);
    }


    public void deletar(Integer idEndereco){ // PROBLEMA AO DELETAR UM ENDERECO QUE ESTÁ RELACIONADO COM UM EMPREGADO
        Endereco endereco = this.getEndereco(idEndereco);
        this.enderecoRepository.delete(endereco);
    }
}
