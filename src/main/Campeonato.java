import java.util.Arrays;
import java.util.Objects;

/**
 * Classe que cria o tipo Campeonato. Ela contém os tipos nome, times, qtdeParticipantes e maxParticipantes.
 * @author Geraldo de Lima Junior - Matrícula 122110399.
 */
public class Campeonato {
    private final String nome;
    private final int maxParticipantes;
    private int qtdeParticipantes = 0;
    private Time[] times;

    /**
     * Construtor de um campeonato.
     * @param nome do campeonato.
     * @param maxParticipantes máximo de participantes que podem estar no campeonato.
     */
    public Campeonato(String nome, int maxParticipantes) {
        this.nome = nome;
        this.times = new Time[maxParticipantes];
        this.maxParticipantes = maxParticipantes;
    }

    /**
     * Construtor de campeonato que recebe apenas o nome. É utilizado nos métodos que checam
     * se já existem um campeonato com aquele nome.
     * @param nome do campeonato.
     */

    public Campeonato(String nome) {
        this(nome, 0); // chama o construtor anterior.
    }

    /**
     * Adiciona um time ao campeonato.
     * @param equipe time que será adicionado ao campeonato.
     */
    public void adicionaTime(Time equipe){
        if(this.qtdeParticipantes >= 0 && this.qtdeParticipantes < this.maxParticipantes){
            this.times[qtdeParticipantes] = equipe;
            this.qtdeParticipantes++;
        }else{
            throw new ArrayIndexOutOfBoundsException("NUMERO MAXIMO DE PARTICIPANTES JÁ ATINGIDO. TIME NÃO ADICIONADO");
        }
    }

    /**
     * Checa se o número máximo de participantes do campeonato já foi atingido.
     * @return true, se o máximo de participantes foi atingido, ou false, se o máximo de participantes não foi atingido.
     */

    public boolean capacidadeAtingida(){
        if(this.qtdeParticipantes < this.maxParticipantes){
            return false;
        }
        return true;
    }

    /**
     * Checa se um time está contido em um campeonato.
     * @param timeTestado time que será procurado dentro do campeonato.
     * @return true, se timeTestado estiver contido no campeonato, ou false, se timeTestado
     * não estiver contido no campeonato.
     */
    public boolean contemTime(Time timeTestado){
        if (Arrays.asList(times).contains(timeTestado)){
            return true;
        }
        return false;
    }

    /**
     * Sobrescreve o método equals(), utilizando os nomes dos campeonatos para determinar se são iguais.
     * @param o objeto que será comparado.
     * @return true, se os objetos comparados forem iguais, ou false, se os objetos comparados forem desiguais.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campeonato that = (Campeonato) o;
        return Objects.equals(nome.toUpperCase(), that.nome.toUpperCase()); // compara se os campeonatos têm o mesmo nome, ignorando diferenças entre letras maiúsculas e minúsculas
    }

    /**
     * Sobrescreve o método hashcode(), utilizando o nome para determinar o hashcode do objeto.
     * @return hashcode gerado a partir do nome do campeonato.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public String getNome(){
        return this.nome;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    public Time[] getTimes() {
        return times;
    }

    public int getQtdeParticipantes() {
        return qtdeParticipantes;
    }
}