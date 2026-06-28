package com.imc.model;

public interface Calculavel {

    /**
     * Calcula o IMC da entidade.
     * @return valor do IMC calculado
     */
    double calcularIMC();

    /**
     * Retorna a classificação do IMC de acordo com as regras específicas da entidade.
     * @return String com a classificação
     */
    String classificarIMC();

    /**
     * Exibe um relatório formatado com os dados e o IMC da entidade.
     */
    void exibirRelatorio();
}
