import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        System.out.println("Inicializando sistema com dados de exemplo...");
        biblioteca.cadastrarLivro("Dom Casmurro", "Machado de Assis", 1899, "1234567890", 3);
        biblioteca.cadastrarLivro("O Cortiço", "Aluísio Azevedo", 1890, "0987654321", 2);
        biblioteca.cadastrarLivro("O Alienista", "Machado de Assis", 1882, "1122334455", 1);

        biblioteca.cadastrarUsuario("João Silva", "1001", "Aluno");
        biblioteca.cadastrarUsuario("Maria Santos", "2001", "Professor");
        biblioteca.cadastrarUsuario("Pedro Oliveira", "1002", "Aluno");

        System.out.println("\nSistema inicializado com sucesso!\n");

        do {
            System.out.println("=== SISTEMA DE BIBLIOTECA ===");
            System.out.println("1. Listar Livros");
            System.out.println("2. Cadastrar Livro");
            System.out.println("3. Cadastrar Usuário");
            System.out.println("4. Realizar Empréstimo");
            System.out.println("5. Realizar Devolução");
            System.out.println("6. Reservar Livro");
            System.out.println("7. Listar Empréstimos Ativos");
            System.out.println("8. Listar Reservas");
            System.out.println("9. Histórico de Usuário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    biblioteca.listarLivros();
                    break;

                case 2:
                    System.out.print("Título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Ano: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Quantidade disponível: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();

                    biblioteca.cadastrarLivro(titulo, autor, ano, isbn, quantidade);
                    break;

                case 3:
                    System.out.print("Nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Matrícula/ID: ");
                    String matricula = scanner.nextLine();
                    System.out.print("Tipo (Aluno/Professor): ");
                    String tipo = scanner.nextLine();

                    biblioteca.cadastrarUsuario(nome, matricula, tipo);
                    break;

                case 4:
                    System.out.print("ISBN do livro: ");
                    String isbnEmprestimo = scanner.nextLine();
                    System.out.print("Matrícula/ID do usuário: ");
                    String matriculaEmprestimo = scanner.nextLine();

                    biblioteca.realizarEmprestimo(isbnEmprestimo, matriculaEmprestimo);
                    break;

                case 5:
                    System.out.print("ISBN do livro: ");
                    String isbnDevolucao = scanner.nextLine();
                    System.out.print("Matrícula/ID do usuário: ");
                    String matriculaDevolucao = scanner.nextLine();

                    biblioteca.realizarDevolucao(isbnDevolucao, matriculaDevolucao);
                    break;

                case 6:
                    System.out.print("ISBN do livro: ");
                    String isbnReserva = scanner.nextLine();
                    System.out.print("Matrícula/ID do usuário: ");
                    String matriculaReserva = scanner.nextLine();

                    biblioteca.reservarLivro(isbnReserva, matriculaReserva);
                    break;

                case 7:
                    biblioteca.listarEmprestimosAtivos();
                    break;

                case 8:
                    biblioteca.listarReservas();
                    break;

                case 9:
                    System.out.print("Matrícula/ID do usuário: ");
                    String matriculaHistorico = scanner.nextLine();

                    biblioteca.listarHistoricoUsuario(matriculaHistorico);
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

            System.out.println();

        } while (opcao != 0);

        scanner.close();
    }
}