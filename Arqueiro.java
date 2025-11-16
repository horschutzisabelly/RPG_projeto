
public class Arqueiro extends Personagem {

    public Arqueiro() {
        super("Nia", 100, 16, 7, 1);
    }

    public Arqueiro(String nome) {
        super(nome, 100, 16, 7, 1);
    }

    public Arqueiro(Arqueiro outro) {
        super(outro);
    }

    @Override
    public String getDescricaoClasse() {
        return "Arqueiro (Batedora)";
    }

    @Override
    public void usarHabilidadeEspecial(Personagem oponente) {
        System.out.println(this.nome + " dispara uma 'Flecha Envenenada'!");

        int danoBase = 5;
        oponente.receberDano(danoBase);
        System.out.println(oponente.getNome() + " recebe " + danoBase + " de dano da flecha.");

        if (dado.nextBoolean()) {
            int danoVeneno = this.nivel * 3;
            System.out.println("O veneno faz efeito! " + oponente.getNome() + " recebe " + danoVeneno + " de dano extra.");
            oponente.receberDano(danoVeneno);
        } else {
            System.out.println("O veneno não fez efeito.");
        }
    }

    @Override
    public Arqueiro clone() {
        return new Arqueiro(this);
    }
}
