package pt.amane.junit5test.blog.negocio;

import pt.amane.junit5test.blog.modelo.Notificacao;

public interface GerenciadorNotificacao {
    void enviar(Notificacao notificacao);
}
