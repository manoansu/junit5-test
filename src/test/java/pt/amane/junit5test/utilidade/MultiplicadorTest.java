package pt.amane.junit5test.utilidade;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MultiplicadorTest {

    @ParameterizedTest
    @EnumSource(value = Multiplicador.class, names= {"DOBRO", "TRIPLO"})
    void applicarMultiplicador(Multiplicador multiplicador) {
        assertNotNull(multiplicador.aplicarMultiplicador(10.0));
    }

    @ParameterizedTest
    @EnumSource(value = Multiplicador.class)
    void applicarMultiplicadorTodos(Multiplicador multiplicador) {
        assertNotNull(multiplicador.aplicarMultiplicador(10.0));
    }

}