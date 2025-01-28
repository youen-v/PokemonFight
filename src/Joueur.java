import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
    private String name;
    private Pokemon choice_pokemon;

    public Joueur(String nom){
        this.name = nom;
        this.choice_pokemon = null;
    }

    public String getName() {
        return name;
    }

    public void getChoice_pokemon(ArrayList<Pokemon> pokemon_list) {
        if (choice_pokemon != null) {
            System.out.println("Vous avez déjà sélectionné " + choice_pokemon.getNom());
            return;
        }

        System.out.println("Liste des Pokémon disponibles :");
        for (int i = 0; i < pokemon_list.size(); i++) {
            System.out.println((i + 1) + ": " + pokemon_list.get(i));
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
}
