package pt.amane.junit5test.utilidade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {

    @Test
    public void assercaoAgrupada() {

        Pessoa pessoa = new Pessoa("Alex",  "Silva");

        assertEquals("Alex", pessoa.getNome());
        assertEquals("Silva", pessoa.getSobrenome());

    }

}