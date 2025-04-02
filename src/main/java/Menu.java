import consoleColors.ConsoleColors;

import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private Integer choix;

    public Menu() {}

    public void afficherMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Choix joueur");
        System.out.println("2. Combat rapide");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");

        choix = scanner.nextInt();
        choixModeDeJeu(choix);
    }

    public void choixModeDeJeu(Integer choix){
        switch (choix){
            case 0:
                System.out.println("Exit");
                System.exit(0);
                break;
            case 1:
                combatCustom();
                break;
            case 2:
                combatRapide();
                break;
            default:
                System.out.println("-- Relancer le jeu --");
                System.exit(0);
        }
    }

    public void combatCustom(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choix Joueur");

        System.out.println("Nom du joueur 1 : ");
        String nomJoueur1 = scanner.next();

        System.out.println("Nom du joueur 2 : ");
        String nomJoueur2 = scanner.next();

        Joueur joueur_1 = new Joueur(nomJoueur1, new Pokedex(Main.class.getClassLoader().getResourceAsStream("pokedex.json")));
        Joueur joueur_2 = new Joueur(nomJoueur2, new Pokedex(Main.class.getClassLoader().getResourceAsStream("pokedex.json")));
    }

    public void combatRapide(){
        System.out.println("Combat Rapide");
        // Instanciation du joueur 1
        Joueur joueur_1 = new Joueur("Sacha", new Pokedex(Main.class.getClassLoader().getResourceAsStream("pokedex.json")));
        // Instanciation du joueur 2
        Joueur joueur_2 = new Joueur("Joris", new Pokedex(Main.class.getClassLoader().getResourceAsStream("pokedex.json")));

        joueur_1.getChoixPokemon();
        joueur_2.getChoixPokemon();

        System.out.println(ConsoleColors.GREEN + "Le combat commence" + ConsoleColors.RESET);
        System.out.println("-----------------------------------------------");
        System.out.println("\n");
        new Combat(joueur_1, joueur_2).lancementTourDeJeu();
    }

    public Integer getChoix() {
        return choix;
    }

    // Sous Menu
    public int sousMenu(TourDeJeu tour, Joueur joueur){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menu Pokemon : ");
        System.out.print("| 1. Choix attaque |");
        System.out.println(" - | 2. Infos pokemon |");
        System.out.print("| 3. Pokedex  ");
        System.out.println("     | - | 4. Inventaire    |");
        System.out.print("| 5. Fuir |");
        System.out.print("Votre choix : ");

        switch (scanner.next()){
            case "1" : {
                tour.choixAttaque(joueur);
                return 1;
            }
            case "2" : {
                joueur.getPokemonChoisi().getInfos();
                try {
                    sousMenu(tour, joueur);
                } catch (Exception e) {
                    System.out.println(ConsoleColors.RED + "Erreur : " + e.getMessage());
                }
                return 2;
            }
            case "3" : {
                // Attention appel la methode pour faire des dégâts
                // changer le remove du pokemon a seulement si KO et pas a la sélèction
                joueur.getChoixPokemon();
                return 3;
            }
            case "4" : {
                System.out.println("Mon inventaire");
                return 4;
            }
            case "5" : {
                System.out.println("Vous fuyez le combat à toute jambes");
                System.exit(0);
                return 5;
            }
        }
        return 0;
    }
}
