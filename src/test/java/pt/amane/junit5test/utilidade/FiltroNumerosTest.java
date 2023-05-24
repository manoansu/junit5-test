package pt.amane.junit5test.utilidade;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FiltroNumerosTest {

    @Test
    public void deveRetornarNumerosPares() {

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4);
        List<Integer> numerosParesEsperado = Arrays.asList(2,4);
        List<Integer> resultadoFiltro = FiltroNumeros.numerosPares(numeros);

        assertIterableEquals(numerosParesEsperado, resultadoFiltro);
    }
    @Test
    public void deveRetornarNumerosImpares() {

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4);
        List<Integer> numerosParesEsperado = Arrays.asList(1,3);
        List<Integer> resultadoFiltro = FiltroNumeros.numerosImpares(numeros);

        assertIterableEquals(numerosParesEsperado, resultadoFiltro);
    }

    @Test
    public void deveRetornarValorPositivo() {
        int numero = 5;
        boolean positivovalor = FiltroNumeros.isPositivo(numero);
        assertTrue(positivovalor);
    }

    @Test
    public void deveRetornarValorNegativo() {
        int numero = -5;
        boolean negativovalor = FiltroNumeros.isPositivo(numero);
        assertFalse(negativovalor);
    }

}