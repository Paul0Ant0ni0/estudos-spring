package org.soulcodeacademy.empresa.controllers;


import org.soulcodeacademy.empresa.domain.Empregado;
import org.soulcodeacademy.empresa.domain.Projeto;
import org.soulcodeacademy.empresa.domain.dto.EmpregadoDTO;
import org.soulcodeacademy.empresa.domain.dto.ProjetoDTO;
import org.soulcodeacademy.empresa.services.EmpregadoService;
import org.soulcodeacademy.empresa.services.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping("/projetos")
    public List<Projeto> listar(){
        return this.projetoService.listar();
    }

    @GetMapping("/projetos/{idProjeto}")
    public Projeto getProjeto(@PathVariable Integer idProjeto){
        return this.projetoService.getProjeto(idProjeto);
    }

    @PostMapping("/projetos")
    public Projeto salvar(@Valid @RequestBody ProjetoDTO dto){
        return this.projetoService.salvar(dto);
    }

    @PutMapping ("/projetos/{idProjeto}")
    public Projeto atualizar(@PathVariable Integer idProjeto, @Valid @RequestBody ProjetoDTO dto){
        return this.projetoService.atualizar(idProjeto, dto);
    }

    @DeleteMapping("/projetos/{idProjeto}")
    public void deletar(@PathVariable Integer idProjeto){
        this.projetoService.deletar(idProjeto);
    }
}
