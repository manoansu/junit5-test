package pt.amane.junit5test.utilidade;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class SimuladorEsperaTest {

    @Test
//    @Disabled("Não é mais aplicavel")
//    @EnabledIfEnvironmentVariable(named = "ENV", matches = "DEV")
    public void deveEsperarNaoDarTimeOut() {

//        Assumptions.assumeTrue("PROD".equals(System.getenv("ENV")), () -> "Abortando teste: Não deve ser executado em PROD");
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofMillis(10)));
    }

}