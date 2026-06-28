package com.imc.exception;

public class DadosInvalidosException extends RuntimeException {

    private final String campo;

    public DadosInvalidosException(String mensagem, String campo) {
        super(mensagem);
        this.campo = campo;
    }

    public DadosInvalidosException(String mensagem) {
        super(mensagem);
        this.campo = "desconhecido";
    }

    public String getCampo() {
        return campo;
    }

    @Override
    public String toString() {
        return "[ERRO - Campo: " + campo + "] " + getMessage();
    }
}
