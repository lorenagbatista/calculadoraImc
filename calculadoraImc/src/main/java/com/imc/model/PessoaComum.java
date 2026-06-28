package com.imc.model;

public class PessoaComum extends Pessoa {

    public PessoaComum(String nome, int idade, double peso, double altura) {
        super(nome, idade, peso, altura);
    }

    @Override
    public String getTipoPessoa() {
        return "Pessoa Comum";
    }

}
