
public class Inimigo extends Personagem {

    public Inimigo() {
        super("Rato do Esgoto", 15, 3, 1, 1);
    }

    public Inimigo(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        super(nome, pontosVida, ataque, defesa, nivel);
    }

    public Inimigo(Inimigo outro) {
        super(outro);
    }

    @Override
    public String getDescricaoClasse() {
        return "Inimigo";
    }

    @Override
    public void usarHabilidadeEspecial(Personagem oponente) {
        System.out.println(this.nome + " usa 'Ataque Selvagem!!'");

        int dano = this.ataque + (dado.nextInt(6) + 1);
        int danoFinal = dano - (oponente.defesa / 2);

        if (danoFinal <= 0) danoFinal = 1;

        System.out.println(this.nome + " causa " + danoFinal + " de dano perfurante!");
        oponente.receberDano(danoFinal);
    }

    @Override
    public Inimigo clone() {
        return new Inimigo(this);
    }
}
