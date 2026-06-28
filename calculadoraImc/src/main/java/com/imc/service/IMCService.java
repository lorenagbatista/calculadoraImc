package com.imc.service;

import com.imc.history.HistoricoIMC;
import com.imc.model.Atleta;
import com.imc.model.Pessoa;
import com.imc.model.PessoaComum;

public class IMCService {

    private final HistoricoIMC historico;

    public IMCService() {
        this.historico = new HistoricoIMC();
    }

    public PessoaComum calcularPessoaComum(String nome, int idade,
                                            double peso, double altura) {
        PessoaComum p = new PessoaComum(nome, idade, peso, altura);
        p.exibirRelatorio();
        historico.adicionar(p);
        return p;
    }

    public Atleta calcularAtleta(String nome, int idade, double peso, double altura,
                                  String modalidade, int anosExp) {
        Atleta a = new Atleta(nome, idade, peso, altura, modalidade, anosExp);
        a.exibirRelatorio();
        historico.adicionar(a);
        return a;
    }

    public void exibirHistorico() {
        historico.exibir();
    }

    public int getTotalRegistros() {
        return historico.getTotalRegistros();
    }

    public void exibirTabelaClassificacao() {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("    Tabela de Classificação IMC (OMS)       ");
        System.out.println("--------------------------------------------");
        System.out.println("   IMC < 18,5          Abaixo do peso       ");
        System.out.println("   18,5 ≤ IMC < 25,0   Normal               ");
        System.out.println("   25,0 ≤ IMC < 30,0   Sobrepeso            ");
        System.out.println("   30,0 ≤ IMC < 35,0   Obesidade Grau I     ");
        System.out.println("   35,0 ≤ IMC < 40,0   Obesidade Grau II    ");
        System.out.println("   IMC ≥ 40,0          Obesidade Grau III   ");
        System.out.println("--------------------------------------------");
        System.out.println("        Tabela Ajustada — Atletas           ");
        System.out.println("--------------------------------------------");
        System.out.println("   IMC < 17,0          Abaixo do peso       ");
        System.out.println("   17,0 ≤ IMC < 24,9   Normal               ");
        System.out.println("   24,9 ≤ IMC < 29,9   Sobrepeso muscular   ");
        System.out.println("   29,9 ≤ IMC < 34,9   Elevado              ");
        System.out.println("   IMC ≥ 34,9          Muito elevado        ");
        System.out.println("--------------------------------------------");
        System.out.println();
    }

    public void demonstrarPolimorfismo(Pessoa p1, Pessoa p2) {
        System.out.println();
        System.out.println(" Demonstração de Polimorfismo ");
        System.out.printf("  %s → IMC: %.2f → %s%n",
                p1.getTipoPessoa(), p1.calcularIMC(), p1.classificarIMC());
        System.out.printf("  %s → IMC: %.2f → %s%n",
                p2.getTipoPessoa(), p2.calcularIMC(), p2.classificarIMC());
        System.out.println(" Mesmo IMC, classificações diferentes por sobrescrita!");
        System.out.println("---------------------------------------------");
        System.out.println();
    }
}
