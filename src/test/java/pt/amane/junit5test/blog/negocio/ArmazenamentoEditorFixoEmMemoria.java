package pt.amane.junit5test.blog.negocio;

import pt.amane.junit5test.blog.armazenamento.ArmazenamentoEditor;
import pt.amane.junit5test.blog.modelo.Editor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Classe de dependencia stub ou seja dependencia duble oiuu falsa...
 */
public class ArmazenamentoEditorFixoEmMemoria implements ArmazenamentoEditor {

    public boolean chamouSalvar;

    @Override
    public Editor salvar(Editor editor) {
        this.chamouSalvar = true;
        if (editor.getId() == null) {
            editor.setId(1L);
        }
        return editor;
    }

    @Override
    public Optional<Editor> encontrarPorId(Long editor) {
        return Optional.empty();
    }

    @Override
    public Optional<Editor> encontrarPorEmail(String email) {
        if (email.equals("alex.existe@email.com")) {
            return Optional.of(new Editor(2L, "Alex", "alex.existe@email.com", BigDecimal.TEN, true));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Editor> encontrarPorEmailComIdDiferenteDe(String email, Long id) {
        return Optional.empty();
    }

    @Override
    public void remover(Long editorId) {

    }

    @Override
    public List<Editor> encontrarTodos() {
        return null;
    }
}