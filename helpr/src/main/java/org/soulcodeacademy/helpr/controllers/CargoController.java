package org.soulcodeacademy.helpr.controllers;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.dto.CargoDTO;
import org.soulcodeacademy.helpr.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController // Retorno de dados
public class CargoController {
    @Autowired // está injetando no construtor e criando uma instacia
    private CargoService cargoService;

    //Endpoint é o endereco que será acessado no backend
    @GetMapping("/oi") // com base em localhost:8080/oi retorna a string | Get = leitura de dados, Mapping = mapeamento dos dados
    public String dizola(){
        return "Olá, tudo bem?"; // resposta da requisição
    }
    @GetMapping("/batata")
    public Integer valor(){
        return 1000; // resposta da requisição
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FUNCIONARIO')")
    @GetMapping("/cargos")
    public List<Cargo> listar(){
        // Requisição -> Controller -> Service -> Repository -> SELECT * FROM cargo;
        return this.cargoService.listar(); // JSON = Javascript Object Notation
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FUNCIONARIO')")
    @GetMapping("cargos/{idCargo}") // Indica que o valor após a barra é dinamico
    public Cargo getCargo(@PathVariable Integer idCargo){
        // @PathVariable -> Extrai do endpoint o valor dinâmico
        return this.cargoService.getCargo(idCargo);
    }

    // Podemos utilizar o mesmo endpoint para verbos direfentes
    @PreAuthorize("hasRole('ROLE_ADMIN')") // PERMITE O ACESSO SE O PERFIL É ADMINISTRADOR
    @PostMapping("/cargos") // REQUISIÇÃO TIPO POST para /cargos
    public Cargo salvar(@Valid @RequestBody CargoDTO cargo){
        // @RequestBody -> extrai o JSON do corpo e converte para cargo (deserialização)
        Cargo cargoSalvo = this.cargoService.salvar(cargo);
        return cargoSalvo; // A resposta será cargo inserido.
    }

    // Mapeia requisições do método Put
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/cargos/{idCargo}") // /cargo/5
    public Cargo atualizar(@PathVariable Integer idCargo,
                           @Valid @RequestBody CargoDTO cargo){
        Cargo atualizado = this.cargoService.atualizar(idCargo, cargo);
        return atualizado;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("cargos/{idCargo}") // verbo DELETE no /cargo/1
    public void deletar(@PathVariable Integer idCargo){
        this.cargoService.deletar(idCargo);
    }
}
