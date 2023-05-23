package pt.amane.junit5test.blog.armazenamento;

import pt.amane.junit5test.blog.modelo.Post;

import java.util.List;
import java.util.Optional;

/*
 * Local onde são armazenados os posts
 */
public interface ArmazenamentoPost {
    Post salvar(Post post);
    Optional<Post> encontrarPorId(Long post);
    void remover(Long postId);
    List<Post> encontrarTodos();
}
