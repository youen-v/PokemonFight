import consoleColors.ConsoleColors;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
    private final String name;
    private Pokemon choice_pokemon;

    public Joueur(String nom){
        this.name = nom;
        this.choice_pokemon = null;
    }

    public String getNom() {
        return name;
    }

    public void getChoixPokemon(ArrayList<Pokemon> pokemon_list) {
        if (choice_pokemon != null) {
            System.out.println("Vous avez déjà sélectionné " + choice_pokemon.getNom());
            return;
        }

        System.out.println("Liste des Pokémon disponibles :");
        for (int i = 0; i < pokemon_list.size(); i++) {
            System.out.println((i + 1) + ": " + pokemon_list.get(i).getNom());
        }

        System.out.println("Veuillez choisir un Pokémon (entrez le numéro) :");
        Scanner scanner = new Scanner(System.in);
        int choix = (scanner.nextInt() - 1);

        if (choix >= 0 && choix < pokemon_list.size()) {
            choice_pokemon = pokemon_list.get(choix);
            System.out.println("Le Pokémon attribué à " + this.name + " est : " + choice_pokemon.getNom());
            System.out.println("\n---------------------------------------------------\n");
            pokemon_list.remove(choix);
        } else {
            System.out.println("Aucun Pokémon n'a été choisi.");
        }
    }

    public Pokemon getPokemonChoisi() {
        return choice_pokemon;
    }

    public void essaiJoueur (ArrayList<Pokemon> pokedex) {
        for (int essaie = 0; essaie < 3; essaie++) {
            // Appel de la méthode de la classe Joueur pour choisir un pokemon
            System.out.println("Choisi ton pokemon " + this.getNom());
            this.getChoixPokemon(pokedex);
            //
            Pokemon pokeChoice = this.getPokemonChoisi();

            if (pokeChoice != null) {
                essaie = 3;
            } else if (essaie < 2) {
                System.out.println(ConsoleColors.RED + "Il vous reste " + (3 - (essaie + 1)) + " essaie" + ConsoleColors.RESET);
            } else {
                System.out.println("Aurevoir");
                System.exit(0);
            }
        }
    }
}
