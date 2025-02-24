import consoleColors.ConsoleColors;
import java.util.Scanner;

public class Menu {
    private Integer choix;

    public Menu() {
        System.out.println("Bienvenue dans le Menu");
        afficherMenu();
    }

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

        Joueur joueur_1 = new Joueur(nomJoueur1);
        Joueur joueur_2 = new Joueur(nomJoueur2);
    }

    public void combatRapide(){
        System.out.println("Combat Rapide");
        // Instanciation du joueur 1
        Joueur joueur_1 = new Joueur("Sacha");
        // Instanciation du joueur 2
        Joueur joueur_2 = new Joueur("Joris");

        joueur_1.getChoixPokemon(new Pokedex(Main.class.getClassLoader().getResourceAsStream("pokedex.json")));
        joueur_2.getChoixPokemon(new Pokedex(Main.class.getClassLoader().getResourceAsStream("pokedex.json")));

        System.out.println(ConsoleColors.GREEN + "Le combat commence" + ConsoleColors.RESET);
        System.out.println("-----------------------------------------------");
        System.out.println("\n");
        new Combat(joueur_1, joueur_2).lancementTourDeJeu();
    }

    public Integer getChoix() {
        return choix;
    }
}
