package com.saldobancario.saldobancario.controller;

import com.saldobancario.saldobancario.model.SaldoBancarioModel;
import com.saldobancario.saldobancario.model.dto.SaldoBancarioDto;
import com.saldobancario.saldobancario.services.SaldoBancarioService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class SaldoBancarioController {
    @Autowired
    SaldoBancarioService saldoBancarioService;

    //ENDPOINTS
    //REQUISIÇÕES GET

    //método para mostrar todas as contas
    @GetMapping("/")
    public Iterable<SaldoBancarioDto> exibirTodasAsContas(){
        return saldoBancarioService.exibirTodas();
    }

    //método para mostrar uma conta específica
    @GetMapping ("/{id}")
public Optional<SaldoBancarioDto> buscarContaPorId(@PathVariable( name="id") String id){
        return saldoBancarioService.buscarPorId(id);
    }

    // REQUISIÇÃO POST
    //método para cadastrar uma nova conta
    @PostMapping( "/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> cadastrarNovaConta(@RequestBody SaldoBancarioDto contaBancariaDto){
        saldoBancarioService.cadastrar(contaBancariaDto);
        return ResponseEntity.ok("Conta-corrente criada com sucesso.");
    }

    //REQUISIÇÃO PUT
    // método para atualizar valor por depósito
    @PatchMapping ( "/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SaldoBancarioModel> fazerDeposito(@PathVariable( name="id") String id, @RequestBody double valorFornecido){
        SaldoBancarioModel deposito = saldoBancarioService.depositar(id,valorFornecido);

        if(deposito != null){
            return ResponseEntity.ok(deposito);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // método para atualizar valor por saque
    @PatchMapping ( "/{id}/saque")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SaldoBancarioModel> fazerSaque(@PathVariable String id, @RequestBody double valorFornecido){
        SaldoBancarioModel saque = saldoBancarioService.sacar(id,valorFornecido);

        if(saque != null && saque.getSaldo() >= valorFornecido){
            return ResponseEntity.ok(saque);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    //REQUISIÇÃO DELETE
    // método para excluir uma conta bancária
    @DeleteMapping( "/{id}")
    public void excluirConta(@PathVariable String id){
        saldoBancarioService.excluir(id);
    }
}