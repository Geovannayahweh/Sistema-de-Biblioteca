import java.util.Date;
import java.util.Calendar;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private boolean devolvido;

    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataEmprestimo);
        calendar.add(Calendar.DAY_OF_MONTH, usuario.getPrazoDevolucao());
        this.dataDevolucao = calendar.getTime();

        this.devolvido = false;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public boolean isAtivo() {
        return !devolvido;
    }

    public String toString() {
        return "Livro: " + livro.getTitulo() + " | Usuário: " + usuario.getNome() +
                " | Data Empréstimo: " + dataEmprestimo + " | Data Devolução: " + dataDevolucao +
                " | Status: " + (devolvido ? "Devolvido" : "Ativo");
    }
}