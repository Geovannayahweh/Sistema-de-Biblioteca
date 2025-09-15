import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;
    private List<Reserva> reservas;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public void cadastrarLivro(String titulo, String autor, int ano, String isbn, int quantidadeDisponivel) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                System.out.println("Erro: Já existe um livro com este ISBN.");
                return;
            }
        }

        Livro novoLivro = new Livro(titulo, autor, ano, isbn, quantidadeDisponivel);
        livros.add(novoLivro);
        System.out.println("Livro cadastrado com sucesso: " + titulo);
    }

    public void cadastrarUsuario(String nome, String matriculaId, String tipo) {
        for (Usuario usuario : usuarios) {
            if (usuario.getMatriculaId().equals(matriculaId)) {
                System.out.println("Erro: Já existe um usuário com esta matrícula/ID.");
                return;
            }
        }

        Usuario novoUsuario;
        if (tipo.equalsIgnoreCase("Aluno")) {
            novoUsuario = new Aluno(nome, matriculaId);
        } else if (tipo.equalsIgnoreCase("Professor")) {
            novoUsuario = new Professor(nome, matriculaId);
        } else {
            System.out.println("Erro: Tipo de usuário inválido. Use 'Aluno' ou 'Professor'.");
            return;
        }

        usuarios.add(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso: " + nome + " (" + tipo + ")");
    }

    public void realizarEmprestimo(String isbn, String matriculaId) {
        Livro livro = buscarLivroPorIsbn(isbn);
        Usuario usuario = buscarUsuarioPorMatricula(matriculaId);

        if (livro == null) {
            System.out.println("Erro: Livro não encontrado.");
            return;
        }

        if (usuario == null) {
            System.out.println("Erro: Usuário não encontrado.");
            return;
        }

        if (!livro.estaDisponivel()) {
            System.out.println("Erro: Livro não disponível para empréstimo.");
            return;
        }

        for (Emprestimo emp : emprestimos) {
            if (emp.getUsuario().getMatriculaId().equals(matriculaId) &&
                    emp.getLivro().getIsbn().equals(isbn) &&
                    emp.isAtivo()) {
                System.out.println("Erro: Usuário já possui este livro emprestado.");
                return;
            }
        }

        Emprestimo emprestimo = new Emprestimo(livro, usuario);
        emprestimos.add(emprestimo);
        livro.decrementarQuantidade();

        System.out.println("Empréstimo realizado com sucesso:");
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("Data de Devolução: " + emprestimo.getDataDevolucao());
    }

    public void realizarDevolucao(String isbn, String matriculaId) {
        Emprestimo emprestimo = null;

        for (Emprestimo emp : emprestimos) {
            if (emp.getUsuario().getMatriculaId().equals(matriculaId) &&
                    emp.getLivro().getIsbn().equals(isbn) &&
                    emp.isAtivo()) {
                emprestimo = emp;
                break;
            }
        }

        if (emprestimo == null) {
            System.out.println("Erro: Empréstimo não encontrado ou já devolvido.");
            return;
        }

        emprestimo.setDevolvido(true);
        emprestimo.getLivro().incrementarQuantidade();

        System.out.println("Devolução realizada com sucesso:");
        System.out.println("Livro: " + emprestimo.getLivro().getTitulo());
        System.out.println("Usuário: " + emprestimo.getUsuario().getNome());

        processarReservas(emprestimo.getLivro());
    }

    public void reservarLivro(String isbn, String matriculaId) {
        Livro livro = buscarLivroPorIsbn(isbn);
        Usuario usuario = buscarUsuarioPorMatricula(matriculaId);

        if (livro == null) {
            System.out.println("Erro: Livro não encontrado.");
            return;
        }

        if (usuario == null) {
            System.out.println("Erro: Usuário não encontrado.");
            return;
        }

        if (livro.estaDisponivel()) {
            System.out.println("Livro disponível para empréstimo. Não é necessário reservar.");
            return;
        }

        for (Reserva res : reservas) {
            if (res.getUsuario().getMatriculaId().equals(matriculaId) &&
                    res.getLivro().getIsbn().equals(isbn)) {
                System.out.println("Erro: Usuário já possui reserva para este livro.");
                return;
            }
        }

        Reserva reserva = new Reserva(livro, usuario);
        reservas.add(reserva);

        System.out.println("Reserva realizada com sucesso:");
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Usuário: " + usuario.getNome());
    }

    private void processarReservas(Livro livro) {
        Reserva reservaParaProcessar = null;

        for (Reserva reserva : reservas) {
            if (reserva.getLivro().getIsbn().equals(livro.getIsbn())) {
                reservaParaProcessar = reserva;
                break;
            }
        }

        if (reservaParaProcessar != null) {
            System.out.println("Processando reserva para o livro: " + livro.getTitulo());
            reservas.remove(reservaParaProcessar);
            realizarEmprestimo(livro.getIsbn(), reservaParaProcessar.getUsuario().getMatriculaId());
        }
    }

    public void listarLivros() {
        System.out.println("\n=== LISTAGEM DE TODOS OS LIVROS ===");
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    public void listarEmprestimosAtivos() {
        System.out.println("\n=== EMPRÉSTIMOS ATIVOS ===");
        boolean encontrouAtivos = false;

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.isAtivo()) {
                System.out.println(emprestimo);
                encontrouAtivos = true;
            }
        }

        if (!encontrouAtivos) {
            System.out.println("Nenhum empréstimo ativo no momento.");
        }
    }

    public void listarHistoricoUsuario(String matriculaId) {
        Usuario usuario = buscarUsuarioPorMatricula(matriculaId);
        if (usuario == null) {
            System.out.println("Erro: Usuário não encontrado.");
            return;
        }

        System.out.println("\n=== HISTÓRICO DE EMPRÉSTIMOS DE " + usuario.getNome().toUpperCase() + " ===");
        boolean encontrouEmprestimos = false;

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().getMatriculaId().equals(matriculaId)) {
                System.out.println(emprestimo);
                encontrouEmprestimos = true;
            }
        }

        if (!encontrouEmprestimos) {
            System.out.println("Nenhum empréstimo encontrado para este usuário.");
        }
    }

    public void listarReservas() {
        System.out.println("\n=== RESERVAS ATIVAS ===");
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva ativa no momento.");
        } else {
            for (Reserva reserva : reservas) {
                System.out.println(reserva);
            }
        }
    }

    private Livro buscarLivroPorIsbn(String isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    private Usuario buscarUsuarioPorMatricula(String matriculaId) {
        for (Usuario usuario : usuarios) {
            if (usuario.getMatriculaId().equals(matriculaId)) {
                return usuario;
            }
        }
        return null;
    }
}