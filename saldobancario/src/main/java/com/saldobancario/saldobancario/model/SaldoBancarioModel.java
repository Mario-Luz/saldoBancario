package com.saldobancario.saldobancario.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SaldoBancarioModel {

    //chave primária
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //coluna numero da conta
    @Column(length = 20, nullable = false)
    private String numeroConta;

    //coluna agencia
    @Column(length = 6, nullable = false)
    private String agencia;

    //coluna nome
    @Column(length = 200, nullable = false)
    private String nome;

    //coluna saldo
    @Column(length = 40,nullable = false)
    private double saldo;

    // coluna valor fornecido
    @Column (length = 40,nullable = false)
    private double valorFornecido;

    //coluna tipo de serviço
    @Column(length = 200, nullable = false)
    private String tipoServico;

}
