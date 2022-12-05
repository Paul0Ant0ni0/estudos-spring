package org.soulcodeacademy.empresa.services;

import org.soulcodeacademy.empresa.domain.Empregado;
import org.soulcodeacademy.empresa.domain.Endereco;
import org.soulcodeacademy.empresa.domain.Projeto;
import org.soulcodeacademy.empresa.domain.dto.EmpregadoDTO;
import org.soulcodeacademy.empresa.repositories.EmpregadoRepository;
import org.soulcodeacademy.empresa.services.error.RecursosNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpregadoService {

    @Autowired
    private EmpregadoRepository empregadoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ProjetoService projetoService;


    public List<Empregado> listarEmpregados(){
        return this.empregadoRepository.findAll();
    }

    public Empregado getEmpregado(Integer idEmpregado){
        return this.empregadoRepository.findById(idEmpregado)
                .orElseThrow(() -> new RecursosNaoEncontradoError("Empregado não encontrado!"));
    }


    public Empregado salvar(EmpregadoDTO dto){
        Endereco endereco = this.enderecoService.getEndereco(dto.getIdEndereco()); // adicionado um endereço, pois é obrigatório
        Empregado empregado = new Empregado(null, dto.getNome(), dto.getEmail(), dto.getSalario());
        if (dto.getIdProjeto() != null){ // Verificando se o empregado tem um projeto
            Projeto projeto = this.projetoService.getProjeto(dto.getIdProjeto());
            empregado.getProjetos().add(projeto); // Adicionado um novo projeto ao empregado
        }
        empregado.setEndereco(endereco);
        return this.empregadoRepository.save(empregado);
    }

    public Empregado atualizar(Integer idEmpregado, EmpregadoDTO dto){
        Empregado empregadoAtual = this.getEmpregado(idEmpregado);

        if (dto.getIdProjeto() != null){ // Verificando se o empregado tem um projeto
            Projeto projeto = this.projetoService.getProjeto(dto.getIdProjeto());
            empregadoAtual.getProjetos().add(projeto); // Adicionado um novo projeto ao empregado
        }

        Endereco endereco = this.enderecoService.getEndereco(dto.getIdEndereco());
        empregadoAtual.setNome(dto.getNome());
        empregadoAtual.setEmail(dto.getEmail());
        empregadoAtual.setSalario(dto.getSalario());
        empregadoAtual.setEndereco(endereco);

        Empregado empregado = this.empregadoRepository.save(empregadoAtual);
        return empregado;

    }

    public void deletar(Integer idEmpregado){
        Empregado empregado = this.getEmpregado(idEmpregado);
        this.empregadoRepository.delete(empregado);
    }


    public Empregado deletarProjetoDoEmpregado(Integer idProjeto, Integer idEmpregado){
        Empregado empregado = this.getEmpregado(idEmpregado);
        Projeto projeto = this.projetoService.getProjeto(idProjeto);
        empregado.getProjetos().remove(projeto);

        return this.empregadoRepository.save(empregado);

    }

}
