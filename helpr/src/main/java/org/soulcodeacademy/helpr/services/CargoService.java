package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.dto.CargoDTO;
import org.soulcodeacademy.helpr.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;
    // Listar todos
    public List<Cargo> listar(){
        // retorna os dados da tabela em forma de lista
        // SELECT * FROM cargo
        return this.cargoRepository.findAll();
    }

    // listar um pelo id
    public Cargo getCargo(Integer idCargo){
        // SELECT * FROM cargo WHERE idCargo = ?
        // Optional = Pode haver cargo ou não
        Optional<Cargo> cargo = this.cargoRepository.findById(idCargo);
        if (cargo.isEmpty()){// Encontrou o cargo
            // Não encontrou o cargo com o id solicitado
            throw new RuntimeException("O cargo não foi encontrado"); // Uma exceção, causa um erro com a mensagem
        }else {
            return cargo.get(); // Extrair o cargo de dentro do Optional
        }

    }
    // Salvar

    public Cargo salvar(CargoDTO dto){
        // INSERT INTO cargo
        Cargo cargo = new Cargo(null, dto.getNome(), dto.getDescricao(), dto.getSalario());
        Cargo cargoSalvo = this.cargoRepository.save(cargo); // INSERT INTO cargo
        return cargoSalvo;
    }
    // Atualizar
    // Precisa do ID do cargo e dos dados atualizados
    public Cargo atualizar(Integer idCargo, CargoDTO dto){
        // Verificar se o cargo existe
        Cargo cargoAtual = this.getCargo(idCargo);
        cargoAtual.setNome(dto.getNome());
        cargoAtual.setDescricao(dto.getDescricao());
        cargoAtual.setSalario(dto.getSalario());

        // Atualiza a entidade pois possui um ID diferente de nulo
        Cargo atualizado = this.cargoRepository.save(cargoAtual);
        return atualizado;
    }

    // Deletar

    public void deletar(Integer idcargo){
        Cargo cargo = this.getCargo(idcargo);
        this.cargoRepository.delete(cargo);
    }
}

