public class Professor extends Usuario {
    private static final int PRAZO_DEVOLUCAO = 14;

    public Professor(String nome, String matriculaId) {
        super(nome, matriculaId, "Professor");
    }

    public int getPrazoDevolucao() {
        return PRAZO_DEVOLUCAO;
    }
}