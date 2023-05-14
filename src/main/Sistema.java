import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Classe com a função de intermediar o Main e as demais classes que representam tipos de dados que serão utilizados.
 * Ela é controller e repositório do sistema, exercendo funções sobre os times, os campeonatos e as apostas que serão
 * realizadas ao longo da execução do programa
 * @author Geraldo de Lima Junior - Matrícula 122110399.
 */
public class Sistema {
    private static HashMap<String, Time> clubes = new HashMap<>();
    private static HashSet<Campeonato> campeonatos = new HashSet();
    private static ArrayList<Aposta> apostas = new ArrayList<>();

    /**
     * Cria um time e armazena-o no sistema.
     * @param codigo do time que será criado e armazenado.
     * @param nome do time que será criado e armazenado.
     * @param mascote do time que será criado e armazenado.
     * @return uma string que informa se a criação e o armazenamento foram executados, dependendo de o time já existir
     * no sistema.
     */
    public static String criarTime(String codigo, String nome, String mascote) {
        if (clubes.containsKey(codigo)) {
            return "TIME JÁ EXISTE!";
        } else {
            Time time = new Time(codigo, nome, mascote);
            clubes.put(codigo, time);
            return "INCLUSÃO REALIZADA!";
        }
    }

    /**
     * Retorna a forma de String de um time armazenado no sistema, se ele já houver sido armazenado.
     * @param codigo do time que se deseja recuperar.
     * @return uma String com a forma de String do time, se ele existir no sistema, ou "TIME NÃO EXISTE!", se ele
     * não existir no sistema.
     */
    public static String recuperaTime(String codigo) {
        if (clubes.containsKey(codigo)) {
            return clubes.get(codigo).toString();
        }
        return "TIME NÃO EXISTE!";
    }


    /**
     * Cadastra um time em um campeonato, utilizando o nome do campeonato e o código do time a ser adicionado como
     * argumentos.
     * @param nomeCampeonato nome do campeonato que receberá o time.
     * @param codigoTime código do time que será adicionado ao campeonato.
     * @return "TIME NÃO EXISTE!" se o time não estiver no hashmap de clubes cadastrados no sistema,
     * "CAMPEONATO NÃO EXISTE!" se campeonato não estiver cadastrado no set de campeonatos do sistema,
     * "TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS", se o número máximo de clubes do campeonato já houver sido
     * atingido,
     * "TIME INLCUÍDO NO CAMPEONATO", se o time já existir na lista de equipes inscritas no campeonato, não adicionando
     * o time novamente ao campeonato, ou
     * "TIME INLCUÍDO NO CAMPEONATO", se o time e o campeonato existirem e o time não estiver contido na lista de equipes
     * do campeonato, porém, ao contrário da última alternativa, adicionando o time ao campeonato.
     */
    public static String cadastraTimeEmCampeonato(String nomeCampeonato, String codigoTime) {
        if (!clubes.containsKey(codigoTime)) {
            return "TIME NÃO EXISTE!";
        }

        if (!existeCampeonato(nomeCampeonato)) {
            return "CAMPEONATO NÃO EXISTE!";
        }

        for (Campeonato torneio : campeonatos) {
            if (torneio.getNome().equals(nomeCampeonato)) {
                if (torneio.capacidadeAtingida()) {
                    return "TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!";
                } else if (campeonatoContemTime(torneio, codigoTime)) { // time já cadastrado, retorna que já está cadastrado mas não cadastra de novo
                    return "TIME INCLUÍDO NO CAMPEONATO!";
                }else {
                    torneio.adicionaTime(clubes.get(codigoTime));
                    Time timeEscolhido = clubes.get(codigoTime);
                    timeEscolhido.adicionaCampeonato(torneio);
                }
            }
        }
        return "TIME INCLUÍDO NO CAMPEONATO!";
    }


    /**
     * Cria um campeonato e o armazena no sistema.
     * @param nomeCampeonato nome do campeonato a ser criado, que será o identificador desse campeonato.
     * @param capacidadeParticipantes capacidade máxima de equipes participantes no campeonato.
     * @return "CAMPEONATO JÁ EXISTE", se o campeonato já estiver cadastrado no hashset de campeonatos do sistema, ou
     * "CAMPEONATO ADICIONADO", se o campeonato ainda não estiver adicionado na lista de campeonatos do sistema.
     */
    public static String criarCampeonato(String nomeCampeonato, int capacidadeParticipantes) {
        Campeonato novoCampeonato = new Campeonato(nomeCampeonato, capacidadeParticipantes);
        for (Campeonato torneio : campeonatos) {
            if (torneio.equals(novoCampeonato)) {
                return "CAMPEONATO JÁ EXISTE!";
            }
        }
        campeonatos.add(novoCampeonato);
        return "CAMPEONATO ADICIONADO!";
    }

    /**
     * Exibe a lista de campeonatos nos quais um time está inscrito.
     * @param codigoTime código do time que cuja inscrição em campeonatos será checada.
     * @return lista de campeonatos nos quais o time está inscrito, ou "O TIME NÃO EXISTE", se o time não existir.
     */
    public static String exibeCampeonatos(String codigoTime){
        if(clubes.containsKey(codigoTime)){
            Time equipe = clubes.get(codigoTime);
            return "Campeonatos do " + equipe.getNome() + ":" + equipe.imprimeCampeonatos();
        }
        return "O TIME NÃO EXISTE!";
    }

    /**
     * Verifica se um time está contido em um campeonato, utilizando o nome do campeonato e o código do time como
     * argumentos.
     * @param nomeCampeonato nome do campeonato no qual se verificará a existência de um time.
     * @param codigoTime código do time que será procurado no campeonato.
     * @return "TIME NÃO EXISTE!" se o time não estiver no hashmap de clubes cadastrados no sistema,
     * "CAMPEONATO NÃO EXISTE!" se campeonato não estiver cadastrado no set de campeonatos do sistema,
     * "O TIME ESTÁ NO CAMPEONATO!", se time já estiver contido no campeonato,
     * "O TIME NÃO ESTÁ NO CAMPEONATO", se o time ainda não estiver contido no campeonato.
     */
    public static String verificaTimeEmCampeonato(String nomeCampeonato, String codigoTime) {
        if (!clubes.containsKey(codigoTime)){
            return "O TIME NÃO EXISTE!";
        }

        if (!existeCampeonato(nomeCampeonato)){
            return "O CAMPEONATO NÃO EXISTE!";
        }

        for(Campeonato torneio : campeonatos){
            if(torneio.getNome().equals(nomeCampeonato)){
                Time timeChecado = new Time(codigoTime);
                boolean timeEmCampeonato = torneio.contemTime(timeChecado);
                if(timeEmCampeonato){
                    return "O TIME ESTÁ NO CAMPEONATO!";
                }
            }
        }
        return "O TIME NÃO ESTÁ NO CAMPEONATO";
    }

    /**
     * Registra uma aposta no sistema, sendo a colocação do time no campeonato o palpite da aposta.
     * @param codigoTime código do time no qual se apostará.
     * @param nomeCampeonato campeonato no qual se palpitará a colocação do time.
     * @param colocacao na qual o time deverá estar no campeonato para que a aposta seja ganha.
     * @param valor da aposta.
     * @return "TIME NÃO EXISTE!", se o time não estiver no hashmap de clubes cadastrados no sistema,
     * "CAMPEONATO NÃO EXISTE!", se campeonato não estiver cadastrado no set de campeonatos do sistema,
     * "APOSTA NÃO REGISTRADA", se o time e o torneio existirem, mas a colocação na qual se aposta é superior ao número
     * de participantes do campeonato,
     * "APOSTA REGISTRADA", se a aposta for criada e adicionada ao sistema.
     */
    public static String registraAposta(String codigoTime, String nomeCampeonato, int colocacao, String valor) {
        valor = valor.replace(",", ".");

        double dinheiroApostado = Double.parseDouble(valor);

        if (!clubes.containsKey(codigoTime)) {
            return "TIME NÃO EXISTE!";
        }

        if (!existeCampeonato(nomeCampeonato)) {
            return "CAMPEONATO NÃO EXISTE!";
        }

        for(Campeonato torneio : campeonatos){
            if(torneio.getNome().equals(nomeCampeonato) && colocacao > torneio.getMaxParticipantes()){
                return "APOSTA NÃO REGISTRADA!";
            }
        }

        Time equipe = clubes.get(codigoTime);

        for(Campeonato torneio : campeonatos){
            if(torneio.getNome().equals(nomeCampeonato)){
                Aposta novaAposta = new Aposta(torneio, equipe, colocacao, dinheiroApostado);
                apostas.add(novaAposta);
            }
        }

        return "APOSTA REGISTRADA";

    }

    /**
     * Retorna uma lista com todas as apostas cadastradas no sistema.
     * @return lista de todas as apostas no sistema.
     */
    public static String imprimeApostas() {
        String stringRetorno = "";
        int qtdeApostas = 1;
        for(Aposta bet : apostas){
            stringRetorno += qtdeApostas + bet.toString();
            qtdeApostas++;
        }

        return stringRetorno;
    }

    private static boolean existeCampeonato(String nomeCampeonato) {
        Campeonato novoCampeonato = new Campeonato(nomeCampeonato);
        for (Campeonato torneio : campeonatos) {
            if (torneio.equals(novoCampeonato)) {
                return true;
            }
        }
        return false;
    }

    private static boolean campeonatoContemTime(Campeonato torneio, String codigoTime){
        Time timeTestado = new Time(codigoTime);
        return torneio.contemTime(timeTestado);
    }

    public static void limparClubes() { // viabiliza testes
        clubes.clear();
    }

    public static void limpaCampeonatos() {
        campeonatos.clear();
    }

    public static void limpaApostas() {
        apostas.clear();
    }
}