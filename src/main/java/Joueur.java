import consoleColors.ConsoleColors;
import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
    private String name;
    private Pokedex pokedex;
    private Inventaire inventaire;
    private Pokemon choice_pokemon;
    private Integer essai = 0;

    public Joueur(){}

    public Joueur(String nom, Pokedex pokedex, Inventaire inventaire) {
        this.name = nom;
        this.choice_pokemon = null;
        this.pokedex = pokedex;
        this.inventaire = inventaire;
    }

    public String getNom() {
        return name;
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
    }

    public void getChoixPokemon() {

        if (choice_pokemon != null) {
            choice_pokemon = null;
            System.out.println("Changer votre pokemon au combat :");
        }

        ArrayList<Pokemon> pokemonListe = pokedex.getPokedex();

        System.out.println("Liste des Pokémon disponibles :");
        for (int i = 0; i < pokemonListe.size(); i++) {
            if (!pokemonListe.get(i).estKo()){
                System.out.println((i + 1) + ": " + pokemonListe.get(i).getNom());
            }
        }

        System.out.println("Veuillez choisir un Pokémon (entrez le numéro) :");
        Scanner scanner = new Scanner(System.in);
        int choix = (scanner.nextInt() - 1);

        if (choix >= 0 && choix < pokemonListe.size() && !pokemonListe.get(choix).estKo()) {
            choice_pokemon = pokemonListe.get(choix);
            System.out.println("\n---------------------------------------------------");
        } else {
            essaiJoueur(pokemonListe, choix);
            System.out.println("Le Pokémon n'est pas disponible.");
            getChoixPokemon();
        }
    }

    public Pokemon getPokemonChoisi() {
        return choice_pokemon;
    }

    public void resetPokemonChoisi() {
        this.choice_pokemon = null;
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
