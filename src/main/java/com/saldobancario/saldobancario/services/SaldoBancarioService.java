package com.saldobancario.saldobancario.services;

import com.saldobancario.saldobancario.model.SaldoBancarioModel;
import com.saldobancario.saldobancario.model.dto.SaldoBancarioDto;
import com.saldobancario.saldobancario.repository.SaldoBancarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SaldoBancarioService {

    //ATRIBUTO
    @Autowired
    SaldoBancarioRepository saldoBancarioRepository;

    //MÉTODOS

    // exibir todas as contas
    public Iterable<SaldoBancarioDto> exibirTodas(){
        Iterable<SaldoBancarioModel> contas = saldoBancarioRepository.findAll();
        List<SaldoBancarioDto> listaContasDto = new ArrayList<>();

        for(SaldoBancarioModel conta: contas){
            listaContasDto.add(new SaldoBancarioDto(conta));
        }
        return listaContasDto;
    }

    // buscar uma conta por id
    public Optional<SaldoBancarioDto> buscarPorId(String id){
        Optional<SaldoBancarioModel> conta = saldoBancarioRepository.findById(UUID.fromString(id));
        if (conta.isPresent())
            return Optional.of(new SaldoBancarioDto(conta.get()));
        return Optional.empty();
    }

    // cadastrar nova conta
    public SaldoBancarioModel cadastrar(SaldoBancarioDto saldoBancarioDto){
        SaldoBancarioModel saldoBancarioModel = new SaldoBancarioModel();

        saldoBancarioModel.setId(saldoBancarioDto.getId());
        saldoBancarioModel.setNome(saldoBancarioDto.getNome());
        saldoBancarioModel.setNumeroConta(saldoBancarioDto.getNumeroConta());
        saldoBancarioModel.setAgencia(saldoBancarioDto.getAgencia());
        saldoBancarioModel.setSaldo(saldoBancarioDto.getSaldo());
        saldoBancarioModel.setValorFornecido(0.0);
        saldoBancarioModel.setTipoServico("Criação de conta-corrente");

        return saldoBancarioRepository.save(saldoBancarioModel);
    }

    //fazer deposito
    public SaldoBancarioModel depositar(String id, double valorFornecido){
        SaldoBancarioModel conta = saldoBancarioRepository.findById(UUID.fromString(id)).orElse(null);

        if(conta != null){
            double saldoFinal = conta.getSaldo() + valorFornecido;
            conta.setValorFornecido(valorFornecido);
            conta.setSaldo(saldoFinal);
            conta.setTipoServico("Depósito");
            return saldoBancarioRepository.save(conta);
        }
        return null;
    }

    //fazer saque
    public SaldoBancarioModel sacar(String id, double valorFornecido){
        SaldoBancarioModel conta = saldoBancarioRepository.findById(UUID.fromString(id)).orElse(null);

        if(conta != null && conta.getSaldo() >= valorFornecido){
            conta.setValorFornecido(valorFornecido);
            double saldoFinal = conta.getSaldo() - valorFornecido;
            conta.setSaldo(saldoFinal);
            conta.setTipoServico("Saque");
            return saldoBancarioRepository.save(conta);
        }
        return null;
    }

    // excluir uma conta
    public void excluir(String id){
        saldoBancarioRepository.deleteById(UUID.fromString(id));
    }

}
