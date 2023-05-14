import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SistemaTest {


    @BeforeEach
    public void limpaDados() {
        Sistema.limparClubes();
        Sistema.limpaCampeonatos();
        Sistema.limpaApostas();
    }

    @Test
    void criarTimeComSucesso() {
        String criou = Sistema.criarTime("FLA_123", "Flamengo", "Urubu");
        assertEquals("INCLUSÃO REALIZADA!", criou);
    }

    @Test
    void criarTimeQueJaExiste() {
        Sistema.criarTime("FLA_123", "Flamengo", "Urubu");
        String criou = Sistema.criarTime("FLA_123", "Flamengo", "Urubu");
        assertEquals("TIME JÁ EXISTE!", criou);
    }

    @Test
    void criarTimeRepetido() {
        Sistema.criarTime("FLA_123", "Flamengo", "Urubu");
        String criou = Sistema.criarTime("FLA_123", "Flamengo", "Urubu");

        assertEquals("TIME JÁ EXISTE!", criou);
    }

    @Test
    void recuperaTimeExistente() {
        Sistema.criarTime("FLA_123", "Flamengo", "Urubu");
        String timeRecuperado = Sistema.recuperaTime("FLA_123");

        assertEquals("[FLA_123] Flamengo / Urubu", timeRecuperado);
    }

    @Test
    void recuperaTimeInexistente() {
        String timeRecuperado = Sistema.recuperaTime("codigoInexistente");

        assertEquals("TIME NÃO EXISTE!", timeRecuperado);
    }

    @Test
    void recuperaTimeNulo() {
        String timeRecuperado = Sistema.recuperaTime(null);

        assertEquals("TIME NÃO EXISTE!", timeRecuperado);
    }

    @Test
    void cadastraTimeExistenteEmCampeonatoExistente() {
        Sistema.criarTime("FLA_123", "Flamengo", "Urubu");
        Sistema.criarCampeonato("Brasileirao", 10);
        String time = Sistema.cadastraTimeEmCampeonato("Brasileirao", "FLA_123");

        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", time);
    }

    @Test
    void cadastraTimeInexistenteEmCampeonatoExistente() {
        Sistema.criarCampeonato("Brasileirao", 10);
        String time = Sistema.cadastraTimeEmCampeonato("Brasileirao", "FLA_123");

        assertEquals("TIME NÃO EXISTE!", time);
    }

    @Test
    void cadastraTimeExistenteEmCampeonatoInexistente() {
        Sistema.criarTime("FLA_123", "Flamengo", "Urubu");
        String time = Sistema.cadastraTimeEmCampeonato("Brasileirao", "FLA_123");

        assertEquals("CAMPEONATO NÃO EXISTE!", time);
    }

    @Test
    void cadastraTimeRepetidoEmCampeonatoExistente() {
        Sistema.criarTime("FLA_123", "Flamengo", "Urubu");
        Sistema.criarCampeonato("Brasileirao", 10);
        Sistema.cadastraTimeEmCampeonato("Brasileirao", "FLA_123");
        String time = Sistema.cadastraTimeEmCampeonato("Brasileirao", "FLA_123");


        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", time);
    }

    @Test
    void cadastraTimeEmCampeonatoCheio() {
        Sistema.criarTime("FLA_123", "Flamengo", "Urubu");
        Sistema.criarTime("COR_456", "Corinthians", "Gavião");
        Sistema.criarTime("PAL_789", "Palmeiras", "Porco");
        Sistema.criarCampeonato("Brasileirao", 2);
        Sistema.cadastraTimeEmCampeonato("Brasileirao", "FLA_123");
        Sistema.cadastraTimeEmCampeonato("Brasileirao", "COR_456");
        String cadastroFalhou = Sistema.cadastraTimeEmCampeonato("Brasileirao", "PAL_789");


        assertEquals("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!", cadastroFalhou);
    }

    @Test
    void criarCampeonatoInexistente() {
        String campeonatoCriado = Sistema.criarCampeonato("Brasileirão", 10);

        assertEquals("CAMPEONATO ADICIONADO!", campeonatoCriado);
    }

    @Test
    void criarCampeonatoDuplicado() {
        Sistema.criarCampeonato("Brasileirão", 10);
        String campeonatoCriado = Sistema.criarCampeonato("Brasileirão", 10);

        assertEquals("CAMPEONATO JÁ EXISTE!", campeonatoCriado);
    }

    @Test
    void exibeCampeonatosDeTimeExistente() {
        Sistema.criarCampeonato("Brasileirão", 10);
        Sistema.criarCampeonato("Libertadores", 10);
        Sistema.criarTime("FLA_123", "Flamengo", "Urubu");
        Sistema.cadastraTimeEmCampeonato("Brasileirão", "FLA_123");
        Sistema.cadastraTimeEmCampeonato("Libertadores", "FLA_123");

        String campeonatosInscritos = Sistema.exibeCampeonatos("FLA_123");
        assertEquals("Campeonatos do Flamengo:\n" + "* Brasileirão - 1/10\n" + "* Libertadores - 1/10"
                , campeonatosInscritos);
    }

    @Test
    void exibeCampeonatosDeTimeInexistente() {
        Sistema.criarCampeonato("Brasileirão", 10);
        Sistema.criarCampeonato("Libertadores", 10);

        String campeonatosInscritos = Sistema.exibeCampeonatos("FLA_123");
        assertEquals("O TIME NÃO EXISTE!", campeonatosInscritos);

    }

    @Test
    void verificaTimeInexistenteEmCampeonatoExistente() {
        Sistema.criarCampeonato("Brasileirão", 10);
        Sistema.cadastraTimeEmCampeonato("Brasileirão", "FLA_123");
        String verificaTime = Sistema.verificaTimeEmCampeonato("Brasileirão", "FLA_123");

        assertEquals("O TIME NÃO EXISTE!", verificaTime);
    }

    @Test
    void verificaTimeCadastradoEmCampeonato() {
        Sistema.criarTime("FLA_123", "Flamengo", "Urubu");
        Sistema.criarCampeonato("Brasileirão", 10);
        Sistema.cadastraTimeEmCampeonato("Brasileirão", "FLA_123");
        String verificaTime = Sistema.verificaTimeEmCampeonato("Brasileirão", "FLA_123");

        assertEquals("O TIME ESTÁ NO CAMPEONATO!", verificaTime);
    }

    @Test
    void verificaTimeNaoCadastradoEmCampeonato() {
        Sistema.criarTime("FLA_123", "Flamengo", "Urubu");
        Sistema.criarCampeonato("Brasileirão", 10);
        String verificaTime = Sistema.verificaTimeEmCampeonato("Brasileirão", "FLA_123");

        assertEquals("O TIME NÃO ESTÁ NO CAMPEONATO", verificaTime);
    }

    @Test
    void registraApostaTimeInexistente() {
        Sistema.criarCampeonato("Brasileirão", 10);
        String registraAposta = Sistema.registraAposta("FLA_123", "Brasileirão",
                1, "22,34");

        assertEquals("TIME NÃO EXISTE!", registraAposta);
    }

    @Test
    void registraApostaCampeonatoInexistente() {
        Sistema.criarTime("FLA_123", "Flamengo", "Urubu");
        String registraAposta = Sistema.registraAposta("FLA_123", "Brasileirão",
                1, "22,34");

        assertEquals("CAMPEONATO NÃO EXISTE!", registraAposta);
    }

    @Test
    void registraApostaEmPosicaoInexistente() {
        Sistema.criarTime("FLA_123", "Flamengo", "Urubu");
        Sistema.criarCampeonato("Brasileirão", 10);
        String registraAposta = Sistema.registraAposta("FLA_123", "Brasileirão",
                11, "22,34");

        assertEquals("APOSTA NÃO REGISTRADA!", registraAposta);
    }

    @Test
    void registraApostaComSucesso() {
        Sistema.criarTime("FLA_123", "Flamengo", "Urubu");
        Sistema.criarCampeonato("Brasileirão", 10);
        String registraAposta = Sistema.registraAposta("FLA_123", "Brasileirão",
                1, "22,34");

        assertEquals("APOSTA REGISTRADA", registraAposta);
    }

    @Test
    void imprimeApostas() {
        Sistema.criarTime("FLA_123", "Flamengo", "Urubu");
        Sistema.criarTime("COR_123", "Corinthians", "Gavião");
        Sistema.criarTime("PAL_123", "Palmeiras", "Porco");
        Sistema.criarCampeonato("Brasileirão", 10);
        Sistema.registraAposta("FLA_123", "Brasileirão",
                1, "22,34");
        Sistema.registraAposta("COR_123", "Brasileirão",
                2, "123");
        Sistema.registraAposta("PAL_123", "Brasileirão",
                3, "15.32");

        String apostas = Sistema.imprimeApostas();

        assertEquals("1. [FLA_123] Flamengo / Urubu\n" + "Brasileirão 1 / 10\n" +  "R$ 22.34\n" +
                "2. [COR_123] Corinthians / Gavião\n" + "Brasileirão 2 / 10\n" + "R$ 123.0\n" +
                "3. [PAL_123] Palmeiras / Porco\n" + "Brasileirão 3 / 10\n" +  "R$ 15.32\n", apostas);
    }
}