package pt.amane.junit5test.blog.utilidade;

import pt.amane.junit5test.blog.negocio.ProcessadorTexto;

public class ProcessadorTextoSimples implements ProcessadorTexto {
    @Override
    public int quantidadePalavras(String texto) {
        if (texto == null || texto.isBlank()) {
            return 0;
        }
        return texto.split(" ").length;
    }
}