import java.util.Objects;
import java.util.Random;


public abstract class Personagem implements Cloneable {

    protected String nome;
    protected int pontosVida;
    protected int ataque;
    protected int defesa;
    protected int nivel;
    protected Inventario inventario;

    protected static final Random dado = new Random();

    public Personagem(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = new Inventario();
    }

    // Construtor de cópia
    public Personagem(Personagem outro) {
        this.nome = outro.nome;
        this.pontosVida = outro.pontosVida;
        this.ataque = outro.ataque;
        this.defesa = outro.defesa;
        this.nivel = outro.nivel;
        this.inventario = outro.inventario.clone();
    }

    // Getters
    public String getNome() {
        
        return nome; 
    }
        
    public int getPontosVida() { 
        
        return pontosVida; 
    }
        
    public Inventario getInventario() {
        return inventario;
    }
        
    public boolean isVivo() { 
        
        return this.pontosVida > 0; 
        
    }
    public int getAtaque() { 
        
        return ataque; 
        
    }
    public int getDefesa() { 
        
        return defesa; 
        
    }
    public int getNivel() { 
        return nivel; 
        
    }

    public void aumentarAtaque(int valor) {
        this.ataque += valor;
    }

    protected int rolarDadoD20() {
        return dado.nextInt(20) + 1;
    }

    public int calcularAtaque() {
        int rolagemDado = rolarDadoD20();
        int ataqueTotal = this.ataque + rolagemDado;
        System.out.println(this.nome + " rolou " + rolagemDado + " no dado (Ataque total: " + ataqueTotal + ")");
        return ataqueTotal;
    }

    // Se dano > 0 tira vida Se dano < 0 cura 
    public void receberDano(int dano) {
        this.pontosVida -= dano;
        if (this.pontosVida < 0) {
            this.pontosVida = 0;
        }

        if (dano > 0) {
            System.out.println(this.nome + " recebeu " + dano + " de dano. HP restante: " + this.pontosVida);
        }
    }

    
    public void batalhar(Personagem oponente) {
        System.out.println("\nBATALHA INICIADA: " + this.nome + " vs. " + oponente.getNome());

        Personagem atacante = this;
        Personagem defensor = oponente;

        while (atacante.isVivo() && defensor.isVivo()) {
            System.out.println("\n--- Turno de " + atacante.getNome() + " ---");

            int ataqueTotal = atacante.calcularAtaque();
            int defesaTotal = defensor.getDefesa();
            System.out.println(defensor.getNome() + " está defendendo com " + defesaTotal + " de defesa.");

            if (ataqueTotal > defesaTotal) {
                int dano = ataqueTotal - defesaTotal;
                System.out.println("O ataque acertou!");
                defensor.receberDano(dano);
            } else {
                System.out.println(defensor.getNome() + " defendeu o ataque! Nenhum dano sofrido.");
            }

            if (!defensor.isVivo()) {
                System.out.println("\n" + defensor.getNome() + " foi derrotado!");
                break;
            }

            Personagem temp = atacante;
            atacante = defensor;
            defensor = temp;
        }
    }

    public abstract void usarHabilidadeEspecial(Personagem oponente);
    public abstract String getDescricaoClasse();

    @Override
    public String toString() {
        return String.format("Nome: %s [Nível: %d]\nClasse: %s\nHP: %d\nAtaque: %d | Defesa: %d",
                this.nome, this.nivel, this.getDescricaoClasse(), this.pontosVida, this.ataque, this.defesa);
    }

    // métodos obrigatórios
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personagem that = (Personagem) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public Personagem clone() {
        try {
            Personagem copia = (Personagem) super.clone();
            copia.inventario = this.inventario.clone();
            return copia;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
