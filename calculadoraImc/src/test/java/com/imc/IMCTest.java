package com.imc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.imc.exception.DadosInvalidosException;
import com.imc.model.Atleta;
import com.imc.model.PessoaComum;

class IMCTest {

    @Test
    void testIMCCalculoCorreto() {
        PessoaComum p = new PessoaComum("Teste", 30, 70.0, 1.75);
        assertEquals(22.86, p.calcularIMC(), 0.01);
    }

    @Test
    void testClassificacaoNormal() {
        PessoaComum p = new PessoaComum("Maria", 25, 65, 1.70);
        assertEquals("Normal", p.classificarIMC());
    }

    @Test
    void testClassificacaoAbaixoPeso() {
        PessoaComum p = new PessoaComum("João", 20, 50, 1.80);
        assertEquals("Abaixo do peso", p.classificarIMC());
    }

    @Test
    void testClassificacaoSobrepeso() {
        PessoaComum p = new PessoaComum("Carlos", 40, 90, 1.75);
        assertEquals("Sobrepeso", p.classificarIMC());
    }

    @Test
    void testAtletaClassificacaoDiferente() {
        PessoaComum pessoa = new PessoaComum("P", 30, 85, 1.75);
        Atleta atleta = new Atleta("A", 30, 85, 1.75, "Musculação", 5);

        double imcPessoa  = pessoa.calcularIMC();
        double imcAtleta  = atleta.calcularIMC();

        assertEquals(imcPessoa, imcAtleta, 0.001); // mesmo IMC
        assertNotEquals(pessoa.classificarIMC(), atleta.classificarIMC()); // classificações distintas
    }

    @Test
    void testNomeVazioLancaExcecao() {
        assertThrows(DadosInvalidosException.class,
            () -> new PessoaComum("", 25, 70, 1.75));
    }

    @Test
    void testPesoNegativoLancaExcecao() {
        assertThrows(DadosInvalidosException.class,
            () -> new PessoaComum("Ana", 25, -5, 1.75));
    }

    @Test
    void testAlturaZeroLancaExcecao() {
        assertThrows(DadosInvalidosException.class,
            () -> new PessoaComum("Ana", 25, 70, 0));
    }

    @Test
    void testIdadeInvalidaLancaExcecao() {
        assertThrows(DadosInvalidosException.class,
            () -> new PessoaComum("Bob", 0, 70, 1.75));
    }

    @Test
    void testObesidadeGrauI() {
        PessoaComum p = new PessoaComum("X", 40, 110, 1.80);
        assertEquals("Obesidade Grau I", p.classificarIMC());
    }

    @Test
    void testGetTipoPessoa() {
        PessoaComum p = new PessoaComum("Y", 30, 70, 1.75);
        Atleta a      = new Atleta("Z", 25, 80, 1.80, "Natação", 3);
        assertEquals("Pessoa Comum", p.getTipoPessoa());
        assertEquals("Atleta", a.getTipoPessoa());
    }
}
