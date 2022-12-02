package org.soulcodeacademy.empresa.controllers;

import org.soulcodeacademy.empresa.domain.Endereco;
import org.soulcodeacademy.empresa.domain.dto.EnderecoDTO;
import org.soulcodeacademy.empresa.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;


    @GetMapping("/enderecos")
    public List<Endereco> listar(){
        return this.enderecoService.listar();
    }

    @GetMapping("/enderecos/{idEndereco}")
    public Endereco getEndereco(@PathVariable Integer idEndereco){
        return this.enderecoService.getEndereco(idEndereco);
    }

    @PostMapping("/enderecos")
    public Endereco salvar(@Valid @RequestBody EnderecoDTO dto){
        return this.enderecoService.salvar(dto);
    }

    @PutMapping ("/enderecos/{idEndereco}")
    public Endereco atualizar(@PathVariable Integer idEndereco, @Valid @RequestBody EnderecoDTO dto){
        return this.enderecoService.atualizar(idEndereco, dto);
    }

    @DeleteMapping("/enderecos/{idEndereco}")
    public void deletar(@PathVariable Integer idEndereco){
        this.enderecoService.deletar(idEndereco);
    }

}
