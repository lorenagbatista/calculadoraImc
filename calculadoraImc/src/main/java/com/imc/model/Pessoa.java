package com.imc.model;

import com.imc.exception.DadosInvalidosException;

public abstract class Pessoa implements Calculavel {

    private String nome;
    private int    idade;
    private double peso;   // kg
    private double altura; // metros

    public Pessoa(String nome, int idade, double peso, double altura) {
        setNome(nome);
        setIdade(idade);
        setPeso(peso);
        setAltura(altura);
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new DadosInvalidosException("O nome não pode ser vazio.", "nome");
        }
        this.nome = nome.trim();
    }

    public int getIdade() { return idade; }

    public void setIdade(int idade) {
        if (idade <= 0 || idade > 130) {
            throw new DadosInvalidosException(
                    "Idade inválida: " + idade + ". Deve estar entre 1 e 130.", "idade");
        }
        this.idade = idade;
    }

    public double getPeso() { return peso; }

    public void setPeso(double peso) {
        if (peso <= 0 || peso > 700) {
            throw new DadosInvalidosException(
                    "Peso inválido: " + peso + " kg. Deve estar entre 0,1 e 700 kg.", "peso");
        }
        this.peso = peso;
    }

    public double getAltura() { return altura; }

    public void setAltura(double altura) {
        if (altura <= 0 || altura > 3.0) {
            throw new DadosInvalidosException(
                    "Altura inválida: " + altura + " m. Deve estar entre 0,1 e 3,0 m.", "altura");
        }
        this.altura = altura;
    }

    @Override
    public double calcularIMC() {
        return peso / (altura * altura);
    }

    @Override
    public String classificarIMC() {
        return classificacaoPadrao(calcularIMC());
    }

    protected static String classificacaoPadrao(double imc) {
        if (imc < 18.5)       return "Abaixo do peso";
        else if (imc < 25.0)  return "Normal";
        else if (imc < 30.0)  return "Sobrepeso";
        else if (imc < 35.0)  return "Obesidade Grau I";
        else if (imc < 40.0)  return "Obesidade Grau II";
        else                  return "Obesidade Grau III (Mórbida)";
    }

    public abstract String getTipoPessoa();

    @Override
    public void exibirRelatorio() {
        double imc = calcularIMC();
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.printf ("   Relatório IMC — %-24s║%n", getTipoPessoa());
        System.out.println("--------------------------------------------");
        System.out.printf ("   Nome   : %-31s║%n", nome);
        System.out.printf ("   Idade  : %-31s║%n", idade + " anos");
        System.out.printf ("   Peso   : %-31s║%n", String.format("%.1f kg", peso));
        System.out.printf ("   Altura : %-31s║%n", String.format("%.2f m", altura));
        System.out.println("--------------------------------------------");
        System.out.printf ("   IMC    : %-31s║%n", String.format("%.2f", imc));
        System.out.printf ("   Class. : %-31s║%n", classificarIMC());
        System.out.println("--------------------------------------------");
        System.out.println();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | Idade: %d | IMC: %.2f | %s",
                getTipoPessoa(), nome, idade, calcularIMC(), classificarIMC());
    }
}
