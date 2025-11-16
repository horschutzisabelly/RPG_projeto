import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Jogo {

    private Personagem heroi;
    private BufferedReader reader;

    private Inimigo potino;
    private Inimigo cleopatra;

   
    private boolean potinoDerrotado = false;

    public Jogo() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));

       
        this.potino = new Inimigo("Potino, o Eunuco Estrategista", 100, 10, 8, 3);
        this.cleopatra = new Inimigo("Cleópatra, Rainha do Nilo", 200, 18, 12, 5);

       
        Item pocaoGrande = new Item("Poção de Cura Grande", "Restaura 50 HP.", "cura:50", 1);
        potino.getInventario().adicionarItem(pocaoGrande);

       
        Item elixirReal = new Item("Elixir Real", "Restaura 50 HP.", "cura:50", 2);
        Item amuletoNilo = new Item("Amuleto do Nilo", "Amuleto misterioso da Rainha.", "buff_ataque:2", 1);
        cleopatra.getInventario().adicionarItem(elixirReal);
        cleopatra.getInventario().adicionarItem(amuletoNilo);
    }

    // ----------------------------------------------------------------------
    // INICIO DO JOGO
    // ----------------------------------------------------------------------

    public void iniciarJogo() throws IOException {
        System.out.println("==================================================");
        System.out.println("   A CONSPIRAÇÃO DO NILO: A QUEDA DE CLEÓPATRA");
        System.out.println("==================================================");

        escolherHeroi();
        prepararInventarioInicial();
        comecarAventura();
    }

    // ----------------------------------------------------------------------
    // ESCOLHER HERÓI
    // ----------------------------------------------------------------------

    private void escolherHeroi() throws IOException {
        System.out.println("\n[1] Darlan, o Legionário (Guerreiro)");
        System.out.println("[2] Isis, o Sacerdote (Mago)");
        System.out.println("[3] Nia, a Batedora (Arqueiro)");
        System.out.print("Escolha seu heroi (1-3): ");

        int escolha = 0;
        try {
            escolha = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            escolha = 1;
        }

        switch (escolha) {
            case 1: this.heroi = new Guerreiro(); break;
            case 2: this.heroi = new Mago(); break;
            case 3: this.heroi = new Arqueiro(); break;
            default:
                this.heroi = new Guerreiro();
        }

        System.out.println("\nBem-vindo(a), " + heroi.getNome() + "!");
        System.out.println(heroi);
    }

  

    private void prepararInventarioInicial() {
        Item pocaoCura = new Item("Poção de Cura Pequena", "Restaura 25 HP.", "cura:25", 2);
        Item adaga = new Item("Adaga de Bronze", "Uma arma simples.", "arma", 1);

        Inventario bolsa = this.heroi.getInventario();
        bolsa.adicionarItem(pocaoCura);
        bolsa.adicionarItem(adaga);

        System.out.println("\nItens iniciais adicionados:");
        bolsa.listarItens();
    }


    private void comecarAventura() throws IOException {
        System.out.println("\n--- Parte I: Os Corredores do Palácio ---");
        areaPalacio();

        if (!heroi.isVivo()) {
            gameOver();
            return;
        }

        // Ato II — Cleópatra (uma única chance)
        System.out.println("\n--- Parte II: A Sala do Trono ---");
        boolean derrotouCleopatra = batalhaComEscolhas(this.cleopatra);

        if (derrotouCleopatra && !cleopatra.isVivo()) {
            finalDoJogo();
        } else if (!heroi.isVivo()) {
            gameOver();
        } else {
           
            System.out.println("\nVocê fugiu da batalha contra Cleópatra.");
            System.out.println("Você sobreviveu, mas a missão não foi concluída...");
            System.out.println("Fim de jogo.");
        }
    }

   
    private void areaPalacio() throws IOException {
        while (heroi.isVivo() && !potinoDerrotado) {
            System.out.println("\n--- Corredores do Palácio ---");
            System.out.println("HP Atual: " + heroi.getPontosVida());
            System.out.println("[1] Explorar a ala dos servos (seguro)");
            System.out.println("[2] Explorar a ala da guarda (perigoso)");
            System.out.println("[3] Usar item");
            System.out.println("[4] Enfrentar Potino");
            System.out.print("Sua escolha: ");

            int escolha = 0;
            try {
                escolha = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                continue;
            }

            switch (escolha) {
                case 1: explorarAlaServos(); break;
                case 2: explorarAlaGuarda(); break;
                case 3: usarItem(); break;
                case 4:
                    lutarContraPotino();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        if (potinoDerrotado) {
            System.out.println("\nO caminho para a Sala do Trono está livre.");
        }
    }


    private void lutarContraPotino() throws IOException {
        if (potinoDerrotado) {
            System.out.println("Potino já foi derrotado.");
            return;
        }

        System.out.println("Você entra na sala de Potino...");
        boolean derrotouPotino = batalhaComEscolhas(this.potino);

        if (derrotouPotino && !potino.isVivo()) {
            System.out.println("Potino caiu! O caminho agora está livre.");
            potinoDerrotado = true;

            Item chave = new Item("Chave da Sala do Trono", "Abre a sala do trono.", "chave", 1);
            heroi.getInventario().adicionarItem(chave);
        } else if (!heroi.isVivo()) {
            System.out.println("Você foi derrotado por Potino!");
        } else {
            System.out.println("Você escapou da luta com Potino e voltou aos corredores...");
        }
    }

  

    private void explorarAlaServos() {
        System.out.println("\nVocê segue pela ala dos servos...");

        if (Personagem.dado.nextInt(10) < 3) {
            System.out.println("Você encontra uma pequena bolsa com ervas medicinais.");
            Item pocao = new Item("Poção de Cura Pequena", "Restaura 25 HP.", "cura:25", 1);
            heroi.getInventario().adicionarItem(pocao);
            System.out.println("Você obteve uma Poção de Cura Pequena!");
        } else {
            System.out.println("Nada de útil por aqui.");
        }
    }

    private void explorarAlaGuarda() {
        System.out.println("\nVocê avança pela ala da guarda...");

        if (Personagem.dado.nextBoolean()) {
            System.out.println("Um Guarda Romano aparece!");
            Inimigo guarda = new Inimigo("Guarda Romano", 50, 8, 5, 2);

            if (Personagem.dado.nextInt(10) < 4) {
                Item pocao = new Item("Poção de Cura Pequena", "Restaura 25 HP.", "cura:25", 1);
                guarda.getInventario().adicionarItem(pocao);
            }

            try {
                boolean derrotouGuarda = batalhaComEscolhas(guarda);

                if (derrotouGuarda && !guarda.isVivo()) {
                    System.out.println("Você derrotou o guarda!");
                    saquear(guarda);
                } else if (!heroi.isVivo()) {
                    System.out.println("Você caiu na batalha contra o guarda...");
                } else {
                    System.out.println("Você recuou da luta contra o guarda.");
                }
            } catch (IOException e) {
                System.out.println("Erro durante a batalha.");
            }
        } else {
            System.out.println("Apenas um baú vazio.");
        }
    }

 

    private void usarItem() throws IOException {
        Inventario bolsa = heroi.getInventario();

        if (bolsa.getItens().isEmpty()) {
            System.out.println("Inventário vazio.");
            return;
        }

        System.out.println("\n--- Inventário ---");
        java.util.List<Item> itens = bolsa.getItens();
        for (int i = 0; i < itens.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + itens.get(i));
        }
        System.out.println("[0] Cancelar");
        System.out.print("Escolha um item pelo número: ");

        int escolha;
        try {
            escolha = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            System.out.println("Entrada inválida.");
            return;
        }

        if (escolha == 0) return;
        if (escolha < 1 || escolha > itens.size()) {
            System.out.println("Item inválido.");
            return;
        }

        Item item = itens.get(escolha - 1);
        String nomeItem = item.getNome();
        String efeito = item.getEfeito();

        if (efeito.startsWith("cura:")) {
            int cura = Integer.parseInt(efeito.substring(5));
            bolsa.removerItem(nomeItem);
            heroi.receberDano(-cura);
            System.out.println("Você recuperou " + cura + " HP!");
        } else if (efeito.startsWith("buff_ataque:")) {
            int buff = Integer.parseInt(efeito.substring(12));
            bolsa.removerItem(nomeItem);
            heroi.aumentarAtaque(buff);
            System.out.println("Seu ataque aumentou permanentemente em +" + buff + "!");
        } else {
            System.out.println("Este item não pode ser usado agora.");
        }
    }

  

    private boolean batalhaComEscolhas(Inimigo inimigo) throws IOException {
        System.out.println("\n BATALHA INICIADA: " + heroi.getNome() + " vs " + inimigo.getNome() + "" );

        while (heroi.isVivo() && inimigo.isVivo()) {

            System.out.println("\n--- Turno do Herói ---");
            System.out.println("HP " + heroi.getNome() + ": " + heroi.getPontosVida());
            System.out.println("HP " + inimigo.getNome() + ": " + inimigo.getPontosVida());

            System.out.println("[1] Ataque normal");
            System.out.println("[2] Habilidade especial");
            System.out.println("[3] Usar item");
            System.out.println("[4] Fugir");
            System.out.print("Escolha: ");

            int escolha = 0;
            try {
                escolha = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                continue;
            }

            switch (escolha) {
                case 1:
                    turnoDeAtaque(heroi, inimigo);
                    break;
                case 2:
                    heroi.usarHabilidadeEspecial(inimigo);
                    break;
                case 3:
                    usarItem();
                    break;
                case 4:
                    boolean fugiu = tentarFugir(inimigo);
                    if (fugiu) return false;
                    break;
                default:
                    continue;
            }

            if (!inimigo.isVivo()) return true;
            if (!heroi.isVivo()) return false;

            System.out.println("\n--- Turno de " + inimigo.getNome() + " ---");

            if (Personagem.dado.nextInt(100) < 30) {
                inimigo.usarHabilidadeEspecial(heroi);
            } else {
                turnoDeAtaque(inimigo, heroi);
            }
        }

        return heroi.isVivo();
    }

    
    private void turnoDeAtaque(Personagem atacante, Personagem defensor) {
        int ataqueTotal = atacante.calcularAtaque();
        int defesaTotal = defensor.getDefesa();

        System.out.println(defensor.getNome() + " defende com " + defesaTotal + ".");

        if (ataqueTotal > defesaTotal) {
            int dano = ataqueTotal - defesaTotal;
            System.out.println("Ataque acertou!");
            defensor.receberDano(dano);
        } else {
            System.out.println("O ataque foi defendido!");
        }
    }



    private boolean tentarFugir(Inimigo inimigo) {
        int rolagem = Personagem.dado.nextInt(20) + 1;
        System.out.println("\nVocê tenta fugir... (d20 = " + rolagem + ")");

        if (rolagem >= 12) {
            System.out.println("Você conseguiu fugir!");
            return true;
        } else {
            System.out.println("Falhou! " + inimigo.getNome() + " ataca enquanto você tenta fugir!");
            turnoDeAtaque(inimigo, heroi);
            return false;
        }
    }



    private void saquear(Inimigo inimigo) {
        Inventario inv = inimigo.getInventario();

        if (inv.getItens().isEmpty()) {
            System.out.println("Nenhum item encontrado.");
            return;
        }

        System.out.println("\nVocê saqueia os pertences de " + inimigo.getNome() + "...");

        for (Item i : inv.getItens()) {
            heroi.getInventario().adicionarItem(i);
            System.out.println("Pegou: " + i.getNome() + " (x" + i.getQuantidade() + ")");
        }

        inv.getItens().clear();
    }

   
    private void gameOver() {
        System.out.println("\n--- GAME OVER ---");
    }

    private void finalDoJogo() {
        System.out.println("\n--- VITÓRIA! O trono é seu! ---");
    }
}
