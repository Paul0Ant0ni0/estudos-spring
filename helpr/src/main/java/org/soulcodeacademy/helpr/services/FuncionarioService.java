package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.Funcionario;
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
          dto.getFoto(), cargo); // Relacionando a entidade cargo (idCargo) com a entidade funcionario (coluna idCargo) 1:n

    Funcionario funcionarioSalvo = this.funcionarioRepository.save(funcionario);

    return funcionarioSalvo;
   }

   public Funcionario atualizar(Integer idFuncionario, FuncionarioDTO dto){
        // Busca o funcionario com o idFuncionario
        Funcionario funcionarioAtual = this.getFuncionario(idFuncionario);
        // Busca os dados do cargo ao ser alterado
        Cargo cargo = this.cargoService.getCargo(dto.getIdCargo());
        funcionarioAtual.setNome(dto.getNome());
        funcionarioAtual.setEmail(dto.getEmail());
        funcionarioAtual.setCpf(dto.getCpf());
        funcionarioAtual.setSenha(dto.getSenha());
        funcionarioAtual.setFoto(dto.getFoto());
        funcionarioAtual.setCargo(cargo);

        Funcionario atualizado = this.funcionarioRepository.save(funcionarioAtual);
        return atualizado;

    }

    public void deletar(Integer idFuncionario){
        Funcionario funcionario = this.getFuncionario(idFuncionario);
        this.funcionarioRepository.delete(funcionario);
    }

    public List<Funcionario> listaPorFaixaSalarial(Double valor1, Double valor2){
        return this.funcionarioRepository.findBySalarioEntreFaixas(valor1, valor2);
    }
}
