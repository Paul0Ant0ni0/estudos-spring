package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.dto.ClienteDTO;
import org.soulcodeacademy.helpr.repositories.ClienteRepository;
import org.soulcodeacademy.helpr.services.errors.RecursosNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Torna o objeto da classe injetável
public class ClienteService {

    @Autowired // Injeção -> Se não colocar está anotação o repository irá fica vazio
    private ClienteRepository clienteRepository;

    public List<Cliente> listar(){
        return this.clienteRepository.findAll();
    }

    public Cliente getCliente(Integer idCliente){
        // SELECT * FROM usuarios WHERE id = 3
        Optional<Cliente> cliente = this.clienteRepository.findById(idCliente);

        if (cliente.isEmpty()){
            throw new RecursosNaoEncontradoError("Cliente não foi encontrado");
        }else {
            return cliente.get();
        }
    }

    public Cliente salvar(ClienteDTO dto){
        // Criação da entidade cliente, a partir dos dados validados do DTO
        Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpf(), dto.getSenha(),
                dto.getTelefone());

        return this.clienteRepository.save(cliente);

    }

    public Cliente atualizar(Integer idCliente, ClienteDTO dto){
        Cliente clienteAtual = this.getCliente(idCliente);

        clienteAtual.setNome(dto.getNome());
        clienteAtual.setEmail(dto.getEmail());
        clienteAtual.setCpf(dto.getCpf());
        clienteAtual.setSenha(dto.getSenha());
        clienteAtual.setTelefone(dto.getTelefone());

        return this.clienteRepository.save(clienteAtual);

    }


    public void deletar(Integer idCliente){
        Cliente cliente = this.getCliente(idCliente);
        this.clienteRepository.delete(cliente);
    }
}

// Quando usar entidade e dto?
// Entidade = retorno dos dados
// DTO = entrada dos dados
