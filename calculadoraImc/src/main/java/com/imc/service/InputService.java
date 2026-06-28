package com.imc.service;

import java.util.Scanner;

import com.imc.exception.DadosInvalidosException;

public class InputService {

    private final Scanner scanner;

    public InputService(Scanner scanner) {
        this.scanner = scanner;
    }

    public String lerString(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String valor = scanner.nextLine().trim();
                if (valor.isBlank()) {
                    throw new DadosInvalidosException("O campo não pode ser vazio.", "texto");
                }
                return valor;
            } catch (DadosInvalidosException e) {
                System.out.println(e.getMessage() + "Tente novamente.");
            }
        }
    }

    public int lerInteiro(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                String entrada = scanner.nextLine().trim();
                int valor = Integer.parseInt(entrada);
                if (valor < min || valor > max) {
                    throw new DadosInvalidosException(
                        "Valor deve estar entre " + min + " e " + max + ".", "inteiro");
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Informe um número inteiro.");
            } catch (DadosInvalidosException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public double lerDouble(String prompt, double min, double max) {
        while (true) {
            try {
                System.out.print(prompt);
                String entrada = scanner.nextLine().trim().replace(",", ".");
                double valor = Double.parseDouble(entrada);
                if (valor < min || valor > max) {
                    throw new DadosInvalidosException(
                        String.format("Valor deve estar entre %.1f e %.1f.", min, max), "double");
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Informe um número (ex.: 85,5 ou 85.5).");
            } catch (DadosInvalidosException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int lerOpcaoMenu(int min, int max) {
        return lerInteiro("Escolha uma ação: ", min, max);
    }
}
