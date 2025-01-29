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
                System.exit(1);
                break;
            case 1:
                System.out.println("Choix Joueur");
                System.out.println("Nom du joueur 1 : ");
                String nameJ1 = scanner.nextLine();
                joueur_1 = new Joueur(nameJ1);

                System.out.println("Nom du joueur 2 : ");
                String nameJ2 = scanner.nextLine();
                joueur_2 = new Joueur(nameJ2);
                break;
            case 2:
                System.out.println("Combat Rapide");
                // Instanciation du joueur 1
                joueur_1 = new Joueur("Sacha");
                // Instanciation du joueur 2
                joueur_2 = new Joueur("Joris");
                break;
        }
        // Instanciation de la liste de pokemon
        ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();

        // Instanciation et ajout des pokemons à la liste
        // Instanciation Pikachu
        Map<String, Integer> attPika = new HashMap<>();
        attPika.put("Éclair", 40);
        attPika.put("Tonnerre", 60);
        Pokemon pikachu = new Pokemon("Pikachu", "Électrique", 100, attPika, 30);
        pokedex.add(pikachu);
        // Instanciation Bulbizarre
        Map<String, Integer> attBulb = new HashMap<>();
        attBulb.put("Charge", 40);
        attBulb.put("Fouet-Liane", 60);
        Pokemon bulbizarre = new Pokemon("Bulbizarre", "Plante", 120, attBulb, 35);
        pokedex.add(bulbizarre);
        // Instanciation Salamèche
        Map<String, Integer> attSala = new HashMap<>();
        attSala.put("Flammèche", 35);
        attSala.put("Lance-Flammes", 50);
        Pokemon salameche = new Pokemon("Salamèche", "Feu", 110, attSala, 25);
        pokedex.add(salameche);
        // Instanciation Carapuce
        Map<String, Integer> attCara = new HashMap<>();
        attCara.put("Écume", 30);
        attCara.put("Pistolet à O", 40);
        Pokemon carapuce = new Pokemon("Carapuce", "Eau", 115, attCara, 40);
        pokedex.add(carapuce);
        // Instanciation Rondoudou
        Map<String, Integer> attRond = new HashMap<>();
        attRond.put("Ecras'Face", 40);
        attRond.put("Echo", 50);
        Pokemon rondoudou = new Pokemon("Rondoudou", "Normal", 130, attRond, 20);
        pokedex.add(rondoudou);

        for (int essaie = 0; essaie < 3; essaie++) {
            // Appel de la méthode de la classe Joueur pour choisir un pokemon
            System.out.println("Choisi ton pokemon " + joueur_1.getName());
            joueur_1.getChoice_pokemon(pokedex);
            //
            Pokemon pokeChoice = joueur_1.getPokemonChoisi();

            if (pokeChoice != null) {
                essaie = 3;
            } else if (essaie < 2) {
                System.out.println(ConsoleColors.RED + "Il vous reste " + (3 - (essaie + 1)) + " essaie" + ConsoleColors.RESET);
            } else {
                System.out.println("Aurevoir");
                System.exit(0);
            }
        }

        for (int essaie = 0; essaie < 3; essaie++) {
            // Appel de la méthode de la classe Joueur pour choisir un pokemon
            System.out.println("Choisi ton pokemon " + joueur_2.getName());
            joueur_2.getChoice_pokemon(pokedex);
            //
            Pokemon pokeChoice = joueur_2.getPokemonChoisi();

            if (pokeChoice != null) {
                essaie = 3;
            } else if (essaie < 2) {
                System.out.println(ConsoleColors.RED + "Il vous reste " + (3 - (essaie + 1)) + " essaie" + ConsoleColors.RESET);
            } else {
                System.out.println("Aurevoir");
                System.exit(0);
            }
        }

        Combat combat = new Combat(joueur_1, joueur_2);
        int pvpokemon1 = combat.affichePv(joueur_1);
        int pvpokemon2 = combat.affichePv(joueur_2);

        System.out.println(joueur_1.getName() + " à choisi " + joueur_1.getPokemonChoisi());
        System.out.println(joueur_2.getName() + " à choisi " + joueur_2.getPokemonChoisi());

        int tour = 1;

        do {

            System.out.println("\n------------ TOURS " + tour + " ------------");

            System.out.print("Attaque du premier joueur " + joueur_1.getName() + "\n");
            int attaque1 = combat.attaquePokemon(joueur_1, joueur_2);
            System.out.print("Degat de l'attaque de " + joueur_1.getPokemonChoisi() + " : " + attaque1 + "\n");
            pvpokemon2 = Math.max((pvpokemon2 - attaque1), 0);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("\n-----------------------------------------------\n");
            System.out.println(joueur_1.getPokemonChoisi().getNom() + " PV : " + pvpokemon1);
            System.out.println(joueur_2.getPokemonChoisi().getNom() + " PV : " + pvpokemon2);
            System.out.println("\n-----------------------------------------------\n");
            combat.finCombat(pvpokemon2);

            System.out.print("Attaque du deuxieme joueur " + joueur_2.getName() + "\n");
            int attaque2 = combat.attaquePokemon(joueur_2, joueur_1);
            System.out.print("Degat de l'attaque de " + joueur_2.getPokemonChoisi() + " : " + attaque2 + "\n");
            pvpokemon1 = Math.max((pvpokemon1 - attaque2), 0);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("\n-----------------------------------------------\n");
            System.out.println(joueur_1.getPokemonChoisi().getNom() + " PV : " + pvpokemon1);
            System.out.println(joueur_2.getPokemonChoisi().getNom() + " PV : " + pvpokemon2);
            System.out.println("\n-----------------------------------------------\n");
            combat.finCombat(pvpokemon1);

            tour++;

        } while ((pvpokemon2 != 0) || (pvpokemon1 != 0));

    }
}