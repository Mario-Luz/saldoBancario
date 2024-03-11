package com.saldobancario.saldobancario.model.dto;

import com.saldobancario.saldobancario.model.SaldoBancarioModel;
import lombok.Data;

import java.util.UUID;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SaldoBancarioDto implements Serializable {
   

    private UUID id;
    private String numeroConta;
    private String agencia;
    private String nome;
    private double saldo;

    public SaldoBancarioDto(SaldoBancarioModel clienteModel) {
        this.id = clienteModel.getId();
        this.numeroConta = clienteModel.getNumeroConta();
        this.agencia = clienteModel.getAgencia();
        this.nome = clienteModel.getNome();
        this.saldo = clienteModel.getSaldo();

    }

    public SaldoBancarioDto() {
    }


}
