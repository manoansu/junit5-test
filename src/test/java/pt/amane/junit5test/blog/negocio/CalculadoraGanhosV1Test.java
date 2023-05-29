package pt.amane.junit5test.blog.negocio;

import org.junit.jupiter.api.*;
import pt.amane.junit5test.blog.modelo.Editor;
import pt.amane.junit5test.blog.modelo.Ganhos;
import pt.amane.junit5test.blog.modelo.Post;
import pt.amane.junit5test.blog.utilidade.ProcessadorTextoSimples;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CalculadoraGanhosV1Test {

    static CalculadoraGanhos calculadora;
    Editor autor;
    Post post;

    @BeforeAll
    static void beforeAll() {
        calculadora = new CalculadoraGanhos(new ProcessadorTextoSimples(), BigDecimal.TEN);
    }

    @BeforeEach
    void beforeEach() {
        autor = new Editor(1L, "Alex", "alex@gmail.com", new BigDecimal(5), true);

        post = new Post(1L, "Ecossistema Java", "O ecossistema do Java é muito maduro", autor,
                "ecossistema-java-abc123", null, false, false);
    }

    @Test
    public void Dado_um_post_e_autor_premium_Quando_calcular_ganhos_Entao_deve_retornar_valor_com_bonus() {
        Ganhos ganhos = calculadora.calcular(post);
        assertEquals(new BigDecimal("45"), ganhos.getTotalGanho());
    }

    @Test
    public void Dado_um_post_e_autor_Quando_calcular_ganhos_Entao_deve_retornar_quantidade_de_palavras_no_post() {
        Ganhos ganhos = calculadora.calcular(post);
        assertEquals(7, ganhos.getQuantidadePalavras());
    }

    @Test
    public void Dado_um_post_e_autor_Quando_calcular_ganhos_Entao_deve_retornar_valor_pago_por_palavra_do_autor() {
        Ganhos ganhos = calculadora.calcular(post);
        assertEquals(autor.getValorPagoPorPalavra(), ganhos.getValorPagoPorPalavra());
    }

    @Test
    public void Dado_um_post_e_autor_nao_premium_Quando_calcular_ganhos_Entao_deve_retornar_valor_sem_bonus() {
        autor.setPremium(false);
        Ganhos ganhos = calculadora.calcular(post);
        assertEquals(new BigDecimal("35"), ganhos.getTotalGanho());
    }

}
