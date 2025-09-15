public abstract class Usuario {
    private String nome;
    private String matriculaId;
    private String tipo;

    public Usuario(String nome, String matriculaId, String tipo) {
        this.nome = nome;
        this.matriculaId = matriculaId;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getMatriculaId() {
        return matriculaId;
    }

    public String getTipo() {
        return tipo;
    }

    // Métodos de modificação (setters)
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMatriculaId(String matriculaId) {
        this.matriculaId = matriculaId;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public abstract int getPrazoDevolucao();

    public String toString() {
        return "Nome: " + nome + " | Matrícula/ID: " + matriculaId + " | Tipo: " + tipo;
    }
}