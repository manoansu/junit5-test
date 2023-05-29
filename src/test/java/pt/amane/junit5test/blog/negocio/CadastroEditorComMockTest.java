package pt.amane.junit5test.blog.negocio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.amane.junit5test.blog.exception.RegraNegocioException;
import pt.amane.junit5test.blog.modelo.Editor;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Com a anotação ExtendWith(MockitoExtension.class), permite nos ussar as anotações do Mock, InjectMocs, etc..
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class CadastroEditorComMockTest {

    @Spy
    Editor editor = new Editor(null, "Alex", "alex@email.com", BigDecimal.TEN, true);

    @Captor
    ArgumentCaptor<Mensagem> mensagemArgumentCaptor;

    @Mock // com essa anotação não é necessario criar dessa forma ex: armazenamentoEditor = Mockito.mock(ArmazenamentoEditorFixoEmMemoria.class);
    private ArmazenamentoEditorFixoEmMemoria armazenamentoEditor;

    @Mock // com essa anotação não é necessario criar dessa forma ex: gerenciadorEnvioEmail = Mockito.mock(GerenciadorEnvioEmail.class);
    private GerenciadorEnvioEmail gerenciadorEnvioEmail;

    // junta os mocks e cria novas instancia concreta como:
    // cadastroEditor = new CadastroEditor(armazenamentoEditor, gerenciadorEnvioEmail); tamvem cria uma nova instancia a cada teste.
    @InjectMocks
    private CadastroEditor cadastroEditor;

    @BeforeEach
    void setUp() {
        editor = new Editor(null, "Alex", "alex@email.com", BigDecimal.TEN, true);

//        // Passando parametro fixo.
//        Mockito.when(armazenamentoEditor.salvar(editor))
//                .thenReturn(new Editor(1L, "Alex", "alex@email.com", BigDecimal.TEN, true));

        //Passando parametro dinamico substituindo a variavel editor, por Mockito.any(Editor.class)..
        Mockito.when(armazenamentoEditor.salvar(Mockito.any(Editor.class)))
                .thenAnswer(invocacao -> {
                    Editor editorPassado = invocacao.getArgument(0, Editor.class);
                    editorPassado.setId(1L);
                    return editorPassado;
                });
    }

    @Test
    public void Dado_um_editor_valido_Quando_criar_Entao_deve_retornar_um_id_de_cadastro() {
        Editor editorSalvo = cadastroEditor.criar(editor);
        assertEquals(1L, editorSalvo.getId());
    }

    @Test
    public void Dado_um_editor_valido_Quando_criar_Entao_deve_chamar_metodo_salvar_do_armazenamento() {
        cadastroEditor.criar(editor);
        Mockito.verify(armazenamentoEditor, Mockito.times(1))
                .salvar(Mockito.eq(editor));
    }

    @Test
    public void Dado_um_editor_valido_Quando_criar_e_lancar_exception_ao_salvar_Entao_nao_deve_enviar_email() {
        Mockito.when(armazenamentoEditor.salvar(editor))
                        .thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, ()-> cadastroEditor.criar(editor));
        Mockito.verify(gerenciadorEnvioEmail, Mockito.never()).enviarEmail(Mockito.any());
    }

    @Test
    void Dado_um_editor_valido_Quando_criar_e_lancar_exception_ao_salvar_Entao_nao_deve_enviar_email_1_versao() {
        Mockito.when(armazenamentoEditor.salvar(editor)).thenThrow(new RuntimeException());
        assertAll("Não deve enviar e-mail, quando lançar Exception do armazenamento",
                ()->assertThrows(RuntimeException.class, ()-> cadastroEditor.criar(editor)),
                ()->Mockito.verify(gerenciadorEnvioEmail, Mockito.never()).enviarEmail(Mockito.any())
        );
    }

    @Test
    void Dado_um_editor_valido_Quando_cadastrar_Entao_deve_enviar_email_com_destino_ao_editor() {
        Editor editoSalvo = cadastroEditor.criar(editor);

        Mockito.verify(gerenciadorEnvioEmail).enviarEmail(mensagemArgumentCaptor.capture());

        Mensagem mensagem = mensagemArgumentCaptor.getValue();

        assertEquals(editoSalvo.getEmail(), mensagem.getDestinatario());
    }

    @Test
    void Dado_um_editor_valido_Quando_cadastrar_Entao_deve_verificar_o_email() {
        cadastroEditor.criar(editor);
        Mockito.verify(editor, Mockito.atLeast(1)).getEmail();
    }

    @Test
    void Dado_um_editor_com_email_existente_Quando_cadastrar_Entao_deve_lancar_exception() {
        Mockito.when(armazenamentoEditor.encontrarPorEmail("alex@email.com"))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of(editor));
        Editor editorComEmailExistente = new Editor(null, "Alex", "alex@email.com", BigDecimal.TEN, true);
        cadastroEditor.criar(editor);
        assertThrows(RegraNegocioException.class, ()-> cadastroEditor.criar(editorComEmailExistente));
    }
}