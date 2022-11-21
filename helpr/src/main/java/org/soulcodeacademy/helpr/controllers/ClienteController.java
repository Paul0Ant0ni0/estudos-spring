package org.soulcodeacademy.helpr.controllers;

import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.dto.ClienteDTO;
import org.soulcodeacademy.helpr.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController // coletar as requisições
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> listar(){
        return this.clienteService.listar();
    }

    @GetMapping("/clientes/{idCliente}")
    public Cliente getCliente(@PathVariable Integer idCliente){
        return this.clienteService.getCliente(idCliente);
    }

    @PostMapping("/clientes")
    public Cliente salvar(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente clienteSalvo = this.clienteService.salvar(clienteDTO);
        return clienteSalvo;
    }

    @PutMapping("/clientes/{idCliente}")
    public Cliente atualizar(@PathVariable Integer idCliente,
                                 @Valid @RequestBody ClienteDTO clienteDTO){
        Cliente atualizado = this.clienteService.atualizar(idCliente, clienteDTO);
        return atualizado; // CORPO da resposta em JSON
    }

    @DeleteMapping("/clientes/{idCliente}")
    public void deletar(@PathVariable Integer idCliente){
        this.clienteService.deletar(idCliente);
    }
}

// Deserializar => JSON -> Classe
// Serializar => Classe -> JSON