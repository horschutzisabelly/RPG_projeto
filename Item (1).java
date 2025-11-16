import java.util.Objects;

// itens do jogo
public class Item implements Comparable<Item>, Cloneable {

    private String nome;
    private String descricao;
    private String efeito;   
    private int quantidade;

    
    public Item(String nome, String descricao, String efeito, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;
    }

    
    public Item(Item outroItem) {
        this.nome = outroItem.nome;
        this.descricao = outroItem.descricao;
        this.efeito = outroItem.efeito;
        this.quantidade = outroItem.quantidade;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getEfeito() {
        return efeito;
    }

    public int getQuantidade() {
        return quantidade;
    }

    // Setter
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return String.format("%s (x%d): %s. Efeito: %s",
                this.nome, this.quantidade, this.descricao, this.efeito);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        // Considera dois itens iguais se tiverem o mesmo nome
        return Objects.equals(nome, item.nome);
    }

    //métodos obrigatórios
    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public int compareTo(Item outro) {
        return this.nome.compareTo(outro.nome);
    }

    @Override
    public Item clone() {
        return new Item(this);
    }
}
