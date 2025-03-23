import consoleColors.ConsoleColors;
import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
    private String name;
    private Pokemon choice_pokemon;
    private Integer essai = 0;

    public Joueur(){}

    public Joueur(String nom){
        this.name = nom;
        this.choice_pokemon = null;
    }

    public String getNom() {
        return name;
    }

    public void getChoixPokemon(Pokedex pokedex) {
        if (choice_pokemon != null) {
            System.out.println("Vous avez déjà sélectionné " + choice_pokemon.getNom());
            return;
        }

        ArrayList<Pokemon> pokemonListe = pokedex.getPokedex();

        System.out.println("Liste des Pokémon disponibles :");
        for (int i = 0; i < pokemonListe.size(); i++) {
            System.out.println((i + 1) + ": " + pokemonListe.get(i).getNom());
        }

        System.out.println("Veuillez choisir un Pokémon (entrez le numéro) :");
        Scanner scanner = new Scanner(System.in);
        int choix = (scanner.nextInt() - 1);

        if (choix >= 0 && choix < pokemonListe.size()) {
            choice_pokemon = pokemonListe.get(choix);
            System.out.println("\n---------------------------------------------------");
            pokemonListe.remove(choix);
        } else {
            essaiJoueur(pokemonListe, choix);
            System.out.println("Aucun Pokémon n'a été choisi.");
            getChoixPokemon(pokedex);
        }
    }

    public Pokemon getPokemonChoisi() {
        return choice_pokemon;
    }

    public void essaiJoueur (ArrayList listeResponse, int reponse) {
        int maxEssai = 3;

        if (!listeResponse.contains(reponse)) {
            essai++;
            System.out.println(ConsoleColors.RED + "Il vous reste " + (maxEssai - essai) + " essai" + ConsoleColors.RESET);
            if (essai == maxEssai ) {
                System.out.println("Aurevoir");
                System.exit(0);
            }
        }
    }
}
