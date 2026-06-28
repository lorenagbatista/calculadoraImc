package com.imc.model;

import com.imc.exception.DadosInvalidosException;


public class Atleta extends Pessoa {

    private String modalidade;
    private int    anosDeExperiencia;

    public Atleta(String nome, int idade, double peso, double altura,
                  String modalidade, int anosDeExperiencia) {
        super(nome, idade, peso, altura);
        setModalidade(modalidade);
        setAnosDeExperiencia(anosDeExperiencia);
    }

    public String getModalidade() { return modalidade; }

    public void setModalidade(String modalidade) {
        if (modalidade == null || modalidade.isBlank()) {
            throw new DadosInvalidosException("A modalidade não pode ser vazia.", "modalidade");
        }
        this.modalidade = modalidade.trim();
    }

    public int getAnosDeExperiencia() { return anosDeExperiencia; }

    public void setAnosDeExperiencia(int anos) {
        if (anos < 0 || anos > 50) {
            throw new DadosInvalidosException(
                    "Anos de experiência inválido: " + anos, "anosDeExperiencia");
        }
        this.anosDeExperiencia = anos;
    }

    
    @Override
    public String classificarIMC() {
        double imc = calcularIMC();

        if (imc < 17.0)       return "Abaixo do peso (Atleta)";
        else if (imc < 24.9)  return "Normal (Atleta)";
        else if (imc < 29.9)  return "Sobrepeso muscular (Atleta)";
        else if (imc < 34.9)  return "Elevado — avaliar composição corporal";
        else                  return "Muito elevado — recomenda-se avaliação médica";
    }

    @Override
    public String getTipoPessoa() {
        return "Atleta";
    }
    
    @Override
    public void exibirRelatorio() {
        double imc = calcularIMC();
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("           Relatório IMC — Atleta           ");
        System.out.println("--------------------------------------------");
        System.out.printf ("  Nome       : %-27s | %n", getNome());
        System.out.printf ("  Idade      : %-27s | %n", getIdade() + " anos");
        System.out.printf ("  Modalidade : %-27s | %n", modalidade);
        System.out.printf ("  Experiência: %-27s | %n", anosDeExperiencia + " anos");
        System.out.printf ("  Peso       : %-27s | %n", String.format("%.1f kg", getPeso()));
        System.out.printf ("  Altura     : %-27s | %n", String.format("%.2f m", getAltura()));
        System.out.println("--------------------------------------------");
        System.out.printf ("  IMC        : %-27s | %n", String.format("%.2f", imc));
        System.out.printf ("  Class.     : %-27s | %n", classificarIMC());
        System.out.println("--------------------------------------------");
        System.out.println("     Classificação ajustada para atletas    ");
        System.out.println("--------------------------------------------");
        System.out.println();
    }

    @Override
    public String toString() {
        return String.format("[Atleta/%s] %s | %d anos exp. | IMC: %.2f | %s",
                modalidade, getNome(), anosDeExperiencia, calcularIMC(), classificarIMC());
    }
}
