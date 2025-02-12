import java.io.InputStream;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import consoleColors.ConsoleColors;

/**
 * La classe principale du jeu PokemonFight.
 *
 * <p>
 * Cette classe gère le flux principal de l'application. Elle charge les données du Pokedex à partir d'un fichier JSON,
 * affiche un menu principal pour permettre à l'utilisateur de choisir les joueurs ou de démarrer un combat rapide, et
 * orchestre le déroulement du combat entre deux joueurs.
 * </p>
 *
 * <p>
 * Le combat se déroule en alternant les attaques des joueurs jusqu'à ce qu'un Pokémon soit mis KO. Pour la gestion de
 * la coloration dans la console, la classe {@code ConsoleColors} est utilisée.
 * </p>
 *
 * @version 4.0
 */
public class Main {
    /**
     * Point d'entrée de l'application.
     *
     * <p>
     * Cette méthode effectue les opérations suivantes :
     * <ul>
     *   <li>Charge le Pokedex en désérialisant le fichier "pokedex.json" depuis le classpath.</li>
     *   <li>Affiche un menu principal pour permettre à l'utilisateur de choisir entre le choix des joueurs ou un combat rapide.</li>
     *   <li>Crée les instances de {@code Joueur} et permet à chaque joueur de choisir son Pokémon via le Pokedex.</li>
     *   <li>Lance le combat en alternant les tours d'attaque jusqu'à ce qu'un Pokémon soit KO.</li>
     * </ul>
     *
     *
     * @param args les arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        Pokedex pokedex = null;
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("pokedex.json");
        if (inputStream == null) {
            System.err.println("Le fichier pokedex.json est introuvable dans src/main/resources");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            pokedex = mapper.readValue(inputStream, Pokedex.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Choix joueur");
        System.out.println("2. Démarrer un combat");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");

        Joueur joueur_1 = null;
        Joueur joueur_2 = null;

        int choixMenu = scanner.nextInt();
        switch (choixMenu){
            case 0:
                System.out.println("Exit");
                System.exit(0);
                break;
            case 1:
                System.out.println("Choix Joueur");

                System.out.println("Nom du joueur 1 : ");
                String nomJoueur1 = scanner.next();

                System.out.println("Nom du joueur 2 : ");
                String nomJoueur2 = scanner.next();

                joueur_1 = new Joueur(nomJoueur1);
                joueur_2 = new Joueur(nomJoueur2);
                break;
            case 2:
                System.out.println("Combat Rapide");
                // Instanciation du joueur 1
                joueur_1 = new Joueur("Sacha");
                // Instanciation du joueur 2
                joueur_2 = new Joueur("Joris");
                break;
        }

        if (joueur_1 != null && joueur_2 != null) {
            joueur_1.essaiJoueur(pokedex);
            System.out.println(joueur_1.getNom() + " à choisi " + joueur_1.getPokemonChoisi());
            joueur_2.essaiJoueur(pokedex);
            System.out.println(joueur_2.getNom() + " à choisi " + joueur_2.getPokemonChoisi());
        } else {
            System.out.println(ConsoleColors.RED + "AUCUN JOUEUR TROUVE" + ConsoleColors.RESET);
        }

        Combat combat = new Combat();
        int tour = 1;

        do {
            Joueur joueurAtt = (tour % 2 == 0)? joueur_2 : joueur_1;
            Joueur joueurDef = (tour % 2 == 0)? joueur_1 : joueur_2;

            System.out.println("\n------------ TOURS " + tour + " ------------");

            System.out.print("Attaque du joueur " + joueurAtt.getNom() + "\n");
            combat.pointDeVieRestant((combat.attaquePokemon(joueurAtt, joueurDef)), joueurDef);

            System.out.println("\n-----------------------------------------------\n");
            System.out.println(joueurAtt.getPokemonChoisi());
            System.out.println(joueurDef.getPokemonChoisi());
            System.out.println("\n-----------------------------------------------");

            combat.finCombat(joueurDef);
            tour++;

        } while (
                !joueur_1.getPokemonChoisi().estKo() ||
                        !joueur_2.getPokemonChoisi().estKo()
        );

    }
}