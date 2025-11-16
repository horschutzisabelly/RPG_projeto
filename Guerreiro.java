
public class Guerreiro extends Personagem {

    public Guerreiro() {
        super("Darlan", 120, 15, 10, 1);
    }

    public Guerreiro(String nome) {
        super(nome, 120, 15, 10, 1);
    }

    public Guerreiro(Guerreiro outro) {
        super(outro);
    }

    @Override
    public String getDescricaoClasse() {
        return "Guerreiro (Legionário)";
    }

    @Override
    public void usarHabilidadeEspecial(Personagem oponente) {
        System.out.println(this.nome + " usa [Formação de Tartaruga]");
        System.out.println(this.nome + " levanta seu Scutum e se prepara para o impacto.");

        this.defesa += 3;
        System.out.println(this.nome + " ganha +3 de Defesa permanente.");
    }

    @Override
    public Guerreiro clone() {
        return new Guerreiro(this);
    }
}
