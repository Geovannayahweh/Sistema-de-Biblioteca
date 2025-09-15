public class Livro {
    private String titulo;
    private String autor;
    private int ano;
    private String isbn;
    private int quantidadeDisponivel;

    public Livro(String titulo, String autor, int ano, String isbn, int quantidadeDisponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.isbn = isbn;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public void incrementarQuantidade() {
        this.quantidadeDisponivel++;
    }

    public boolean decrementarQuantidade() {
        if (this.quantidadeDisponivel > 0) {
            this.quantidadeDisponivel--;
            return true;
        }
        return false;
    }

    public boolean estaDisponivel() {
        return quantidadeDisponivel > 0;
    }

    public String toString() {
        return "Título: " + titulo + " | Autor: " + autor + " | Ano: " + ano +
                " | ISBN: " + isbn + " | Disponível: " + quantidadeDisponivel;
    }
}