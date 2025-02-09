import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import consoleColors.ConsoleColors;

public class Main {
    public static void main(String[] args) {
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

        // Instantiation de la liste de pokemon
        ArrayList<Pokemon> pokedex = new ArrayList<>();

        // Instanciation et ajout des pokemons à la liste
        // Instanciation Pikachu
        Map<String, Integer> attaquePika = new HashMap<>();
        attaquePika.put("Éclair", 40);
        attaquePika.put("Tonnerre", 60);
        Pokemon pikachu = new Pokemon("Pikachu", "Électrique", 100, attaquePika, 30);
        pokedex.add(pikachu);


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
                joueur_1.getPokemonChoisi().estKo() ||
                        joueur_2.getPokemonChoisi().estKo()
        );

    }
}