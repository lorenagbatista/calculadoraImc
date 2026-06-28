package com.imc.history;

import java.util.ArrayList;
import java.util.List;

import com.imc.model.Pessoa;

public class HistoricoIMC {

    private final List<RegistroIMC> registros = new ArrayList<>();
    
    public void adicionar(Pessoa pessoa) {
        registros.add(new RegistroIMC(pessoa));
    }

    public void exibir() {
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf ("HISTÓRICO DE CÁLCULOS — %d registro(s) na sessão%s | %n",
                registros.size(), " ".repeat(Math.max(0, 43 - String.valueOf(registros.size()).length())));
        System.out.println("----------------------------------------------------------------------------------------");

        if (registros.isEmpty()) {
            System.out.println("Nenhum cálculo realizado ainda.");
        } else {
            exibirRecursivo(0);
        }

        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println();
    }


    private void exibirRecursivo(int indice) {
        if (indice >= registros.size()) {
            return; // caso base
        }
        System.out.println("|  " + registros.get(indice).formatado(indice + 1) + "  |");
        exibirRecursivo(indice + 1);
    }

    public int getTotalRegistros() {
        return registros.size();
    }

    public boolean estaVazio() {
        return registros.isEmpty();
    }
}
