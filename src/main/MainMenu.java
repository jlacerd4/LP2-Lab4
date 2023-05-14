import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        while (true) {
            escolha = menu(scanner);
            decisao(escolha, scanner);
        }
    }

    private static String menu(Scanner scanner) {
        System.out.println("\n(M)Minha inclusão de times\n" +
                "(R)Recuperar time\n" +
                "(.)Adicionar campeonato\n" +
                "(B)Bora incluir time em campeonato e Verificar se time está em campeonato\n" +
                "(E)Exibir campeonatos que o time participa\n" +
                "(T)Tentar a sorte e status\n" +
                "(!)Já pode fechar o programa!");
        return scanner.nextLine().toUpperCase();
    }
    private static void decisao(String escolha, Scanner scanner) {
            try {
                switch (escolha) {
                    case "M":
                        criarTime(scanner);
                        break;
                    case "R":
                        recuperaTime(scanner);
                        break;
                    case ".":
                        criarCampeonato(scanner);
                        break;
                    case "B":
                        adicionarTimeEmCampeonato(scanner);
                        break;
                    case "E":
                        exibirCampeonatos(scanner);
                        break;
                    case "T":
                        gerirApostas(scanner);
                        break;
                    case "!":
                        fechaPrograma();
                    default:
                        throw new IllegalAccessException();
                }
            } catch (IllegalAccessException e) {
                System.out.println("Argumento inválido // APAGAR ANTES DE ENTREGAR.");
            }
    }


    private static void gerirApostas(Scanner scanner) {
        System.out.println("(A)Apostar ou (S)Status das Apostas? ");
        char escolha = scanner.nextLine().toUpperCase().charAt(0);
        if(escolha == 'A'){
            System.out.print("Código: ");
            String codigoTime = scanner.nextLine();
            System.out.print("Campeonato: ");
            String nomeCampeonato = scanner.nextLine();
            System.out.print("Colocação: ");
            int colocacao = scanner.nextInt();
            System.out.print("Valor da aposta: R$");
            String valor = scanner.next();
            scanner.nextLine(); // tira a quebra de linha que sobre do scanner anterior

            System.out.println(Sistema.registraAposta(codigoTime, nomeCampeonato, colocacao, valor));
        }

        if(escolha == 'S'){
            System.out.println(Sistema.imprimeApostas());
        }
    }

    private static void fechaPrograma() {
        System.out.println("Por hoje é só pessoal!");
        System.exit(0);
    }

    // site muito bom: https://www.infoworld.com/article/3305792/comparing-java-objects-with-equals-and-hashcode.html

    private static void recuperaTime(Scanner sc) {

        System.out.print("Código: ");
        String codigo = sc.nextLine();

        String retorno = Sistema.recuperaTime(codigo);
        System.out.println(retorno);
    }

    private static void criarCampeonato(Scanner sc) {

        System.out.println("Campeonato: ");
        String nomeCampeonato = sc.nextLine();
        System.out.println("Participantes: ");
        int numParticipantes = sc.nextInt();
        String retorno = Sistema.criarCampeonato(nomeCampeonato, numParticipantes);
        sc.nextLine();
        System.out.println(retorno);
    }

    private static void adicionarTimeEmCampeonato(Scanner sc) {
        System.out.println("(I) Incluir time em campeonato ou (V) Verificar se time está em campeonato? ");
        char escolha = sc.nextLine().charAt(0);
        if(escolha == 'I'){
            System.out.println("Código: ");
            String codigo = sc.nextLine();
            System.out.println("Campeonato: ");
            String campeonato = sc.nextLine();
            System.out.println(Sistema.cadastraTimeEmCampeonato(campeonato, codigo));

        }else if(escolha == 'V'){
            System.out.println("Código: ");
            String codigoTime = sc.nextLine();
            System.out.println("Campeonato: ");
            String nomeCampeonato = sc.nextLine();
            System.out.println(Sistema.verificaTimeEmCampeonato(nomeCampeonato, codigoTime));;
        }
    }

    private static void exibirCampeonatos(Scanner sc) {
        System.out.println("Time: ");
        String nomeTime = sc.nextLine();
        String campeonatosDoTime = Sistema.exibeCampeonatos(nomeTime);
        System.out.println(campeonatosDoTime);
    }

    private static void criarTime(Scanner sc) {
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Mascote: ");
        String mascote = sc.nextLine();

        String statusCriacao = Sistema.criarTime(codigo, nome, mascote);
        System.out.println(statusCriacao);
    }
}
