package com.imc.ui;

import java.util.Scanner;

import com.imc.exception.DadosInvalidosException;
import com.imc.model.Atleta;
import com.imc.model.PessoaComum;
import com.imc.service.IMCService;
import com.imc.service.InputService;

public class MenuCLI {

    private static final String VERSAO = "1.0.0";

    private final IMCService   imcService;
    private final InputService input;
    private final Scanner      scanner;

    private PessoaComum ultimaPessoaComum;
    private Atleta       ultimoAtleta;

    public MenuCLI() {
        this.scanner    = new Scanner(System.in);
        this.input      = new InputService(scanner);
        this.imcService = new IMCService();
    }

    public void iniciar() {
        exibirBoasVindas();
        loopPrincipal();
        exibirDespedida();
        scanner.close();
    }

    private void loopPrincipal() {
        boolean continuar = true;
        while (continuar) {
            exibirMenu();
            int opcao = input.lerOpcaoMenu(0, 7);
            continuar = processarOpcao(opcao);
        }
    }

    private boolean processarOpcao(int opcao) {
        System.out.println();
        switch (opcao) {
            case 1 -> cadastrarPessoaComum();
            case 2 -> cadastrarAtleta();
            case 3 -> exibirHistorico();
            case 4 -> exibirTabelaClassificacao();
            case 5 -> demonstrarPolimorfismo();
            case 6 -> exibirSobreOSistema();
            case 7 -> exibirAjuda();
            case 0 -> { return false; }
            default -> System.out.println("Opção não reconhecida.");
        }
        pausar();
        return true;
    }

    private void cadastrarPessoaComum() {
        System.out.println("─── Cadastrar Pessoa Comum ───────────────────");
        try {
            String nome   = input.lerString("Nome: ");
            int    idade  = input.lerInteiro("Idade: ", 1, 130);
            double peso   = input.lerDouble("Peso (kg): ", 0.1, 700);
            double altura = input.lerDouble("Altura (m): ", 0.1, 3.0);

            ultimaPessoaComum = imcService.calcularPessoaComum(nome, idade, peso, altura);

        } catch (DadosInvalidosException e) {
            System.out.println("Erro de validação: " + e);
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private void cadastrarAtleta() {
        System.out.println("---- Cadastrar Atleta -----------------------");
        try {
            String nome       = input.lerString("Nome: ");
            int    idade      = input.lerInteiro("Idade: ", 1, 130);
            double peso       = input.lerDouble("Peso (kg): ", 0.1, 700);
            double altura     = input.lerDouble("Altura (m): ", 0.1, 3.0);
            String modalidade = input.lerString("Modalidade: ");
            int    anos       = input.lerInteiro("Anos de experiência: ", 0, 50);

            ultimoAtleta = imcService.calcularAtleta(nome, idade, peso, altura, modalidade, anos);

        } catch (DadosInvalidosException e) {
            System.out.println("Erro de validação: " + e);
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private void exibirHistorico() {
        if (imcService.getTotalRegistros() == 0) {
            System.out.println("Nenhum cálculo realizado ainda nesta sessão.");
            return;
        }
        imcService.exibirHistorico();
    }

    private void exibirTabelaClassificacao() {
        imcService.exibirTabelaClassificacao();
    }

    private void demonstrarPolimorfismo() {
        if (ultimaPessoaComum == null || ultimoAtleta == null) {
            System.out.println("Para demonstrar o polimorfismo, cadastre ao menos uma Pessoa Comum e um Atleta");
            return;
        }
        
        imcService.demonstrarPolimorfismo(ultimaPessoaComum, ultimoAtleta);
    }

    private void exibirSobreOSistema() {
        System.out.println("--------------------------------------------");
        System.out.println("          Calculadora de IMC — Sobre        ");
        System.out.println("--------------------------------------------");
        System.out.printf ("   Versão  : %-30s | %n", VERSAO);
        System.out.println("   Linguagem: Java 17+                      ");
        System.out.println("--------------------------------------------");
        System.out.println("   Conceitos OO demonstrados:               ");
        System.out.println("   ✔ Interface (Calculavel)                 ");
        System.out.println("   ✔ Classe abstrata (Pessoa)               ");
        System.out.println("   ✔ Herança (Atleta extends Pessoa)        ");
        System.out.println("   ✔ Polimorfismo (@Override classificar)   ");
        System.out.println("   ✔ Encapsulamento (private + getters)     ");
        System.out.println("   ✔ Composição (IMCService + Historico)    ");
        System.out.println("   ✔ Exceção personalizada                  ");
        System.out.println("   ✔ Recursão (exibir histórico)            ");
        System.out.println("--------------------------------------------");
    }

    private void exibirAjuda() {
        System.out.println("--------------------------------------------");
        System.out.println("                   Ajuda                    ");
        System.out.println("--------------------------------------------");
        System.out.println("   • Peso  : informe em quilogramas (kg)    ");
        System.out.println("   • Altura: informe em metros (ex: 1.75)   ");
        System.out.println("   • Vírgula ou ponto são aceitos           ");
        System.out.println("   • Fórmula: IMC = Peso ÷ Altura²          ");
        System.out.println("   • Atletas têm tabela ajustada            ");
        System.out.println("--------------------------------------------");
    }

    private void exibirMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("        CALCULADORA DE IMC — MENU           ");
        System.out.printf ("       Registros na sessão: %-14s║%n",
                imcService.getTotalRegistros());
        System.out.println("--------------------------------------------");
        System.out.println("   1. Cadastrar Pessoa Comum                ");
        System.out.println("   2. Cadastrar Atleta                      ");
        System.out.println("   3. Exibir Histórico da Sessão            ");
        System.out.println("   4. Tabela de Classificação IMC           ");
        System.out.println("   5. Demonstrar Polimorfismo               ");
        System.out.println("   6. Sobre o Sistema                       ");
        System.out.println("   7. Ajuda                                 ");
        System.out.println("   0. Sair                                  ");
        System.out.println("--------------------------------------------");
    }

    private void exibirBoasVindas() {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("     Bem-vindo à Calculadora de IMC!        ");
        System.out.println("--------------------------------------------");
        System.out.println("     Versão " + VERSAO + " — Java CLI       ");
        System.out.println("--------------------------------------------");
        System.out.println();
    }

    private void exibirDespedida() {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.printf ("|  Sessão encerrada. %d cálculo(s) realizado | %n",
                imcService.getTotalRegistros());
        System.out.println("Os dados mostrado são apenas estimativas.");
        System.out.println("Para uma avaliação precisa e individualizada, procure um profissional da saúde");
        System.out.println();
    }

    private void pausar() {
        System.out.println("  Pressione ENTER para continuar   ");
        scanner.nextLine();
    }
}
