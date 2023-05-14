/**
 * Classe que cria o tipo Aposta. Ela contém os atributos torneio, equipe, colocação e valorAposta.
 * @author Geraldo de Lima Junior - Matrícula 122110399.
 */

public class Aposta {
    private Campeonato torneio;
    private Time equipe;
    private int colocacao;
    private double valorAposta;

    /**
     * Construtor de aposta
     * @param torneio no qual vai se apostar.
     * @param equipe na qual se vai apostar.
     * @param colocacao na qual se apostará que a equipe terminará o torneio.
     * @param valorAposta valor empenhado na aposta
     */
    public Aposta(Campeonato torneio, Time equipe, int colocacao, double valorAposta){
        this.torneio = torneio;
        this.equipe = equipe;
        this.colocacao = colocacao;
        this.valorAposta = valorAposta;
    }

    /**
     * Formatação desejada da string que representa a aposta.
     * @return uma String no formato [Código-do-time] Nome-do-time / Mascote-do-time
     *                               Torneio-em-que-se-apostou / colocação-apostada / Máximo-de-participantes-do-torneio
     *                               R$ Valor-da-aposta
     *
     */

    @Override
    public String toString() {
        return ". [" + this.equipe.getCodigo() + "] " + this.equipe.getNome() + " / " + this.equipe.getMascote() + "\n"
                + this.torneio.getNome() + " " + this.colocacao + " / "
                + this.torneio.getMaxParticipantes() +  "\n"  +"R$ " + this.valorAposta + "\n";
    }
}
