package padroes.prototype;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegistroModelosTest {

    private RegistroModelos registro;

    @org.junit.jupiter.api.BeforeEach
    void preparar() {
        Contrato locacao = new Contrato("Contrato de Locação", new Parte("Imobiliária Central", "12.345.678/0001-90"));
        locacao.adicionarClausula("Prazo de 30 meses");
        locacao.adicionarClausula("Reajuste anual");

        Procuracao procuracao = new Procuracao("Modelo");
        procuracao.adicionarPoder("Representar perante órgãos públicos");

        registro = new RegistroModelos();
        registro.registrar("locacao", locacao);
        registro.registrar("procuracao", procuracao);
    }

    @Test
    void deveCriarCopiaDistintaDoModelo() {
        ModeloDocumento primeiro = registro.criar("locacao");
        ModeloDocumento segundo = registro.criar("locacao");

        assertNotSame(primeiro, segundo);
        assertEquals(primeiro.resumo(), segundo.resumo());
    }

    @Test
    void deveClonarEstadoInternoSemCompartilharReferencias() {
        Contrato copia = assertInstanceOf(Contrato.class, registro.criar("locacao"));
        copia.getContratante().setNome("Maria Souza");
        copia.adicionarClausula("Multa por rescisão");
        copia.setTitulo("Contrato de Locação - Maria");

        Contrato original = assertInstanceOf(Contrato.class, registro.criar("locacao"));

        assertEquals("Contrato de Locação - Imobiliária Central [cláusulas: 2]", original.resumo());
        assertEquals("Contrato de Locação - Maria - Maria Souza [cláusulas: 3]", copia.resumo());
    }

    @Test
    void deveClonarOutroTipoDeModelo() {
        Procuracao copia = assertInstanceOf(Procuracao.class, registro.criar("procuracao"));
        copia.setOutorgante("João Lima");
        copia.adicionarPoder("Assinar documentos");

        assertEquals("Procuração de João Lima [poderes: 2]", copia.resumo());
        assertEquals("Procuração de Modelo [poderes: 1]", registro.criar("procuracao").resumo());
    }

    @Test
    void deveRejeitarModeloNaoRegistrado() {
        assertThrows(IllegalArgumentException.class, () -> registro.criar("inexistente"));
    }
}
