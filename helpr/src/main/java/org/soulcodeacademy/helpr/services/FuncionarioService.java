package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.dto.CargoDTO;
import org.soulcodeacademy.helpr.domain.dto.FuncionarioDTO;
import org.soulcodeacademy.helpr.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoService cargoService; // Relacionamento entre entidades, por isso a injeção do CargoService

    public List<Funcionario> listar(){
        return this.funcionarioRepository.findAll();
    }

    public Funcionario getFuncionario(Integer idFuncionario){

        Optional<Funcionario> funcionario = this.funcionarioRepository.findById(idFuncionario);

        if (funcionario.isEmpty()){
            throw new RuntimeException("Funcionario não foi encontrado");

        }else {
            return funcionario.get(); // Pega o valor de entidade encontrado
        }
    }

  public Funcionario salvar(FuncionarioDTO dto){
    Cargo cargo = this.cargoService.getCargo(dto.getIdCargo()); // Verifica se o cargo existe mesmo

      // Transferindo informações do DTO para entidade
    Funcionario funcionario = new Funcionario(null, dto.getNome(), dto.getEmail(), dto.getCpf(), dto.getSenha(),
          dto.getFoto(), cargo);

    Funcionario funcionarioSalvo = this.funcionarioRepository.save(funcionario);

    return funcionarioSalvo;
   }
}
