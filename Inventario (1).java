import java.util.ArrayList;
import java.util.Collections;

// Inventário de itens de um Personagem (herói ou inimigo)
public class Inventario implements Cloneable {

    private ArrayList<Item> itens;

    public Inventario() {
        this.itens = new ArrayList<>();
    }

    // Construtor de cópia 
    public Inventario(Inventario outro) {
        this.itens = new ArrayList<>();
        for (Item item : outro.itens) {
            this.itens.add(item.clone());
        }
    }

    public void adicionarItem(Item itemParaAdicionar) {
        // Se já existe um item igual soma quantidades
        for (Item itemExistente : this.itens) {
            if (itemExistente.equals(itemParaAdicionar)) {
                int novaQtd = itemExistente.getQuantidade() + itemParaAdicionar.getQuantidade();
                itemExistente.setQuantidade(novaQtd);
                return;
            }
        }
        // Senão adiciona uma cópia
        this.itens.add(itemParaAdicionar.clone());
    }

    public void removerItem(String nomeDoItem) {
        Item itemParaRemover = null;
        for (Item item : this.itens) {
            if (item.getNome().equalsIgnoreCase(nomeDoItem)) {
                itemParaRemover = item;
                break;
            }
        }

        if (itemParaRemover != null) {
            if (itemParaRemover.getQuantidade() > 1) {
                itemParaRemover.setQuantidade(itemParaRemover.getQuantidade() - 1);
                System.out.println("Usou 1 " + itemParaRemover.getNome() + ".");
            } else {
                this.itens.remove(itemParaRemover);
                System.out.println("Usou o último " + itemParaRemover.getNome() + ".");
            }
        } else {
            System.out.println("Item '" + nomeDoItem + "' não encontrado no inventário.");
        }
    }

    public void listarItens() {
        if (this.itens.isEmpty()) {
            System.out.println("Inventário vazio.");
            return;
        }

        Collections.sort(this.itens);

        System.out.println("--- Inventário ---");
        for (Item item : this.itens) {
            System.out.println(item.toString());
        }
        System.out.println("------------------");
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    //métodos obrigatórios
    @Override
    public Inventario clone() {
        return new Inventario(this);
    }

    @Override
    public String toString() {
        listarItens();
        return "";
    }
}
