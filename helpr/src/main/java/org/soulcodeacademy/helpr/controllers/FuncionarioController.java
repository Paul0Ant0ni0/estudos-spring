package org.soulcodeacademy.helpr.controllers;


import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.dto.FuncionarioDTO;
import org.soulcodeacademy.helpr.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController // está classe é capaz de captar as requisições
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    // /funcionarios (GET)
    @GetMapping("/funcionarios")
    public List<Funcionario> listar(){
        return this.funcionarioService.listar();
    }

    // /funcionarios/{idFuncionarios} (GET)
    @GetMapping("/funcionarios/{idFuncionario}")
    public Funcionario getFuncionario(@PathVariable Integer idFuncionario){
        return this.funcionarioService.getFuncionario(idFuncionario);
    }

    // POST -> Representa a inserção de dados
    @PostMapping("/funcionarios")
    public Funcionario salvar(@Valid @RequestBody FuncionarioDTO funcionario){
        Funcionario funcionarioSalvo = this.funcionarioService.salvar(funcionario);
        return funcionarioSalvo;
    }

    // PUT - Representa a atualização de dados
    @PutMapping("/funcionarios/{idFuncionario}")
    public Funcionario atualizar(@PathVariable Integer idFuncionario,
                                 @Valid @RequestBody FuncionarioDTO funcionario){
        Funcionario atualizado = this.funcionarioService.atualizar(idFuncionario, funcionario);
        return atualizado;
    }

    @DeleteMapping("/funcionarios/{idFuncionario}")
    public void deletar(@PathVariable Integer idFuncionario){
        this.funcionarioService.deletar(idFuncionario);
    }

    // @RequestParam = Captura os valores de parâmetro após ?, ex: /funcionarios/salario?valor1=1000&valor2=2000
    @GetMapping("/funcionarios/salario")
    public List<Funcionario> listarPorFaixaSalarial(@RequestParam Double valor1, @RequestParam Double valor2){
        return this.funcionarioService.listaPorFaixaSalarial(valor1, valor2);
    }
}
