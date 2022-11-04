package org.soulcodeacademy.helpr.controllers;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/cargos")
    public List<Cargo> listar(){
        // Requisição -> Controller -> Service -> Repository -> SELECT * FROM cargo;
        return this.cargoService.listar(); // JSON = Javascript Object Notation
    }

    @GetMapping("cargos/{idCargo}") // Indica que o valor após a barra é dinamico
    public Cargo getCargo(@PathVariable Integer idCargo){
        // @PathVariable -> Extrai do endpoint o valor dinâmico
        return this.cargoService.getCargo(idCargo);
    }

    // Podemos utilizar o mesmo endpoint para verbos direfentes
    @PostMapping("/cargos") // REQUISIÇÃO TIPO POST para /cargos
    public Cargo salvar(@RequestBody Cargo cargo){
        // @RequestBody -> extrai o JSON do corpo e converte para cargo (deserialização)
        Cargo cargoSalvo = this.cargoService.salvar(cargo);
        return cargoSalvo; // A resposta será cargo inserido.
    }

    // Mapeia requisições do método Put
    @PutMapping("/cargos/{idCargo}") // /cargo/5
    public Cargo atualizar(@PathVariable Integer idCargo, @RequestBody Cargo cargo){
        Cargo atualizado = this.cargoService.atualizar(idCargo, cargo);
        return atualizado;
    }
}
