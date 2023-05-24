package pt.amane.junit5test.utilidade;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class SimuladorEsperaTest {

    @Test
    public void deveEsperarNaoDarTimeOut() {

        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofMillis(10)));
    }

}