import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {

    Time equipe;
    Campeonato torneio;

    @BeforeEach
    void criaTime(){
        this.equipe = new Time("FLA_123", "Flamengo", "Urubu");
        this.torneio = new Campeonato("Brasileirao", 2);
    }

    @Test
    void adicionaCampeonato() {
        assertFalse(equipe.getCampeonatos().contains(torneio));
        equipe.adicionaCampeonato(torneio);
        assertTrue(equipe.getCampeonatos().contains(torneio));

    }

    @Test
    void imprimeCampeonatos() {
        Campeonato competicao = new Campeonato("Libertadores", 2);
        equipe.adicionaCampeonato(torneio);
        equipe.adicionaCampeonato(competicao);

        assertEquals("\n* Brasileirao - 0/2\n" + "* Libertadores - 0/2", equipe.imprimeCampeonatos());
    }

}