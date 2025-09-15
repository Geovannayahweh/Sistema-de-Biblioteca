public class Aluno extends Usuario {
    private static final int PRAZO_DEVOLUCAO = 7;

    public Aluno(String nome, String matriculaId) {
        super(nome, matriculaId, "Aluno");
    }

    public int getPrazoDevolucao() {
        return PRAZO_DEVOLUCAO;
    }
}
