import java.util.HashSet;
import java.util.Objects;

/**
 * Classe que cria o tipo Time. Ela é formada por código, nome, mascote e campeonatos nos quais o time participa.
 * @author Geraldo de Lima Junior - Matrícula 122110399.
 */
public class Time {
    private String codigo;
    private String nome;
    private String mascote;
    private HashSet<Campeonato> campeonatos = new HashSet<>();

    /**
     * Representação de um time. É identificado por seu código.
     * @param codigo do time, que será utilizado para identificá-lo.
     * @param nome do time.
     * @param mascote do time.
     */
    public Time(String codigo, String nome, String mascote) {
        this.codigo = codigo;
        this.nome = nome;
        this.mascote = mascote;
    }

    /**
     * Representação do time criada apenas com o seu código. É utilizada para descobrir se um time com o código passado
     * existe em alguma coleção.
     * @param codigo do time, que o identifica.
     */
    public Time(String codigo) {
        this(codigo, null, null);
    }

    /**
     * Adiciona um campeonato à lista de campeonatos nos quais o time está inscrito.
     * @param torneio campeonato no qual o crime está inscrito. Será adicionado à lista de campeonatos nos quais ele
     *                está inscrito.
     */
    public void adicionaCampeonato(Campeonato torneio){
        this.campeonatos.add(torneio);
    }

    /**
     * Retorna a lista de campeonatos nos quais um time está inscrito.
     * @return lista de campeonatos nos quais o time está inscrito.
     */
    public String imprimeCampeonatos(){
        String stringRetorno = "";
        for(Campeonato torneio: this.campeonatos){
            stringRetorno += "\n" + "* " + torneio.getNome() + " - " + torneio.getQtdeParticipantes() + "/" + torneio.getMaxParticipantes();
        }
        return stringRetorno;
    }

    /**
     * Sobrescreve o método toString e atualiza a representação em String do time.
     * @return representação em String do time.
     */
    @Override
    public String toString(){
        return "[" + this.codigo + "] " + this.nome + " / " + this.mascote;
    }

    /**
     * Sobrescreve o método equals(). Compara um time ao outro a partir do seu código.
     * @param o time que será comparado.
     * @return true, se os times tiverem o mesmo nome, ou false, se os times tiverem nomes diferentes, forem de classes
     * diferentes ou o objeto "o" for nulo.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Objects.equals(codigo, time.codigo);
    }

    /**
     * Sobrescreve o método hashCode, utilizando o código para determinar o código hash do objeto
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    public String getNome() {
        return nome;
    }
    public String getCodigo() {
        return codigo;
    }

    public String getMascote() {
        return this.mascote;
    }

    public HashSet<Campeonato> getCampeonatos() {
        return campeonatos;
    }

}
