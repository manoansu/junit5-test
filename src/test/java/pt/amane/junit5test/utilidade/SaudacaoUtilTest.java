package pt.amane.junit5test.utilidade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaudacaoUtilTest {

    @Test
    public void saudar() {
        String saudacao = SaudacaoUtil.saudar(9);
        assertTrue(saudacao.equals("Bom dia"));
        
        saudacao = SaudacaoUtil.saudar(15);
        assertEquals(saudacao, "Boa tarde");
        
        saudacao = SaudacaoUtil.saudar(19);
        assertEquals(saudacao, "Boa noite");
    }
    
    @Test
    public void saudarException() {
        assertThrows(IllegalArgumentException.class, () -> {
             SaudacaoUtil.saudar(25);
        });
    }

}