// Subclasse de Personagem
public class Mago extends Personagem {

    public Mago() {
        super("Isis", 80, 18, 5, 1);
    }

    public Mago(String nome) {
        super(nome, 80, 18, 5, 1);
    }

    public Mago(Mago outro) {
        super(outro);
    }

    @Override
    public String getDescricaoClasse() {
        return "Mago (Sacerdote)";
    }

    @Override
    public void usarHabilidadeEspecial(Personagem oponente) {
        System.out.println(this.nome + " invoca a 'Maldição de Anúbis'!");

        int danoMagico = (this.nivel * 5) + dado.nextInt(10);

        System.out.println("A alma de " + oponente.getNome() + " é afligida, causando " + danoMagico + " de dano!");
        oponente.receberDano(danoMagico);
    }

    @Override
    public Mago clone() {
        return new Mago(this);
    }
}
