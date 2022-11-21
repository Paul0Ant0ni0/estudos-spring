package org.soulcodeacademy.helpr.controllers;

import org.soulcodeacademy.helpr.domain.Chamado;
import org.soulcodeacademy.helpr.domain.dto.ChamadoDTO;
import org.soulcodeacademy.helpr.domain.enums.StatusChamado;
import org.soulcodeacademy.helpr.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping("/chamados")
    public List<Chamado> listar(){
        return this.chamadoService.listarChamado();
    }

    @GetMapping("/chamados/{idChamado}")
    public Chamado getChamado(@PathVariable Integer idChamado){
        return this.chamadoService.getChamado(idChamado);
    }

    @PostMapping ("/chamados")
    public Chamado salvar(@Valid @RequestBody ChamadoDTO chamadoDTO){
        return this.chamadoService.salvar(chamadoDTO);
    }

    @PutMapping ("/chamados/{idChamado}")
    public Chamado atualizar(@PathVariable Integer idChamado, @Valid @RequestBody ChamadoDTO chamadoDTO){
        return this.chamadoService.atualizarChamado(idChamado, chamadoDTO);
    }

    // Listar por cliente

    @GetMapping("/chamados/clientes/{idCliente}")
    public List<Chamado> listarPorCliente(@PathVariable Integer idCliente){
        return this.chamadoService.listarPorCliente(idCliente);
    }

    // Listar por funcionario

    @GetMapping("/chamados/funcionarios/{idFuncionario}")
    public List<Chamado> listarPorFuncionario(@PathVariable Integer idFuncionario){
        return this.chamadoService.listarPorFuncionario(idFuncionario);
    }

    // Calculaodora
    // /soma?n1=200&n2=500 ====> 700
    // ? -> Indica a parte que terá os parametros na URL
    @GetMapping("/soma")
    public Integer soma(@RequestParam Integer n1, @RequestParam Integer n2){
        return n1 + n2;
    }

    // Listar por Status
    @GetMapping("/chamados/statuschamado") //chamados/status?status=ATRIBUIDO
    public List<Chamado> listarPorStatus(@RequestParam StatusChamado status){
        //StatusChamado statusChamado = StatusChamado.valueOf(status);
        return this.chamadoService.listarPorStatus(status);
    }

    

    // Lista por datas {Intervalos}
    // /chamados/intervalo?inicio=2022-01-01&fim=2023-01-01
    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) -> coonverter o valor do parametro para LocalDate
    @GetMapping("/chamados/intervalo")
    public List<Chamado> listarPorIntervaloDatas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim){
        return this.chamadoService.listarPorIntervalosDatas(inicio, fim);
    }

}
