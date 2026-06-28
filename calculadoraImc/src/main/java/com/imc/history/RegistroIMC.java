package com.imc.history;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.imc.model.Pessoa;

public class RegistroIMC {

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Composição: RegistroIMC "tem-uma" Pessoa
    private final Pessoa        pessoa;
    private final double        imc;
    private final String        classificacao;
    private final LocalDateTime dataHora;

    public RegistroIMC(Pessoa pessoa) {
        this.pessoa        = pessoa;
        this.imc           = pessoa.calcularIMC();
        this.classificacao = pessoa.classificarIMC();
        this.dataHora      = LocalDateTime.now();
    }

    public Pessoa        getPessoa()       { return pessoa; }
    public double        getImc()          { return imc; }
    public String        getClassificacao(){ return classificacao; }
    public LocalDateTime getDataHora()     { return dataHora; }

    public String formatado(int numero) {
        return String.format(
            "%3d. [%s] %-20s | IMC: %5.2f | %-35s | %s",
            numero,
            pessoa.getTipoPessoa().substring(0, Math.min(8, pessoa.getTipoPessoa().length())),
            pessoa.getNome(),
            imc,
            classificacao,
            dataHora.format(FORMATO)
        );
    }
}
