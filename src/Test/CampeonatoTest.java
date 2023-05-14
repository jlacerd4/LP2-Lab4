import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CampeonatoTest {

    Campeonato torneio;

    @BeforeEach
    void preparaCampeonato() {
        this.torneio = new Campeonato("Campeonato", 2);
    }

    @Test
    void adicionaTimesDentroDaCapacidade() {
        Time equipe = new Time("FLA_123", "Flamengo", "Urubu");
        Time equipe2 = new Time("COR_456", "Corinthians", "Gavião");

        Time[] participantes = torneio.getTimes();
        assertArrayEquals(new Time[2], participantes); // todos os elementos de ambos os arrays são nulos

        torneio.adicionaTime(equipe);
        assertTrue(Arrays.asList(participantes).contains(equipe)); // a equipe foi adicionada aos participantes

        torneio.adicionaTime(equipe2);
        assertTrue(Arrays.asList(participantes).contains(equipe) && // ambas as equipes foram adicionadas
                Arrays.asList(participantes).contains(equipe2));
    }

    @Test
    void naoAdicionaTimesAcimaDaCapacidade() {
        Time equipe = new Time("FLA_123", "Flamengo", "Urubu");
        Time equipe2 = new Time("COR_456", "Corinthians", "Gavião");
        Time equipe3 = new Time("PAL_123", "Palmeiras", "Porco");

            Time[] participantes = torneio.getTimes();
            assertArrayEquals(new Time[2], participantes); // todos os elementos de ambos os arrays são nulos

            torneio.adicionaTime(equipe);
            assertTrue(Arrays.asList(participantes).contains(equipe)); // a equipe foi adicionada aos participantes

            torneio.adicionaTime(equipe2);
            assertTrue(Arrays.asList(participantes).contains(equipe) && // ambas as equipes foram adicionadas
                    Arrays.asList(participantes).contains(equipe2));

            try{
                torneio.adicionaTime(equipe3);
                fail("DEVERIA FALHAR POIS O NUMERO MAXIMO DE PARTICIPANTES JA FOI ATINGIDO.");
            }catch(ArrayIndexOutOfBoundsException err){
                assertEquals("NUMERO MAXIMO DE PARTICIPANTES JÁ ATINGIDO. TIME NÃO ADICIONADO", err.getMessage());
            }


    }


    @Test
    void capacidadeFoiAtingida() {
        Time equipe = new Time("FLA_123", "Flamengo", "Urubu");
        Time equipe2 = new Time("COR_456", "Corinthians", "Gavião");
        Time[] participantes = torneio.getTimes();

        torneio.adicionaTime(equipe);
        torneio.adicionaTime(equipe2);

        assertTrue(torneio.capacidadeAtingida());
    }

    @Test
    void capacidadeNaoFoiAtingida() {
        Time equipe = new Time("FLA_123", "Flamengo", "Urubu");
        Time[] participantes = torneio.getTimes();

        torneio.adicionaTime(equipe);

        assertFalse(torneio.capacidadeAtingida());
    }

    @Test
    void campeonatoContemTime() {
        Time equipe = new Time("FLA_123", "Flamengo", "Urubu");
        torneio.adicionaTime(equipe);
        assertTrue(torneio.contemTime(equipe));
    }

    @Test
    void campeonatoNaoContemTime() {
        Time equipe = new Time("FLA_123", "Flamengo", "Urubu");
        assertFalse(torneio.contemTime(equipe));
    }

}