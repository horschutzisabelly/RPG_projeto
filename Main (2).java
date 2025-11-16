public class Main {

    public static void main(String[] args) {
        try {
            Jogo jogo = new Jogo();
            jogo.iniciarJogo();
        } catch (Exception e) {
            System.out.println("Erro ao iniciar o jogo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
