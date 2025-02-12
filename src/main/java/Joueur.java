import consoleColors.ConsoleColors;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe {@code Joueur} représente un joueur dans le jeu.
 *
 * <p>Un joueur possède un nom et peut sélectionner un Pokémon parmi ceux disponibles dans le Pokedex.
 * La classe fournit des méthodes pour permettre au joueur de choisir son Pokémon et d'effectuer des essais
 * de sélection. Si le joueur ne choisit pas de Pokémon après trois tentatives, l'application se termine.</p>
 *
 * <p>Exemple d'utilisation :</p>
 * <pre>
 *     Pokedex pokedex = ...;
 *     Joueur joueur = new Joueur("Alice");
 *     joueur.essaiJoueur(pokedex);
 * </pre>
 */
public class Joueur {
    private final String name;
    private Pokemon choice_pokemon;

    /**
     * Constructeur de la classe {@code Joueur}.
     * Initialise le joueur avec le nom fourni et aucun Pokémon choisi.
     *
     * @param nom le nom du joueur
     */
    public Joueur(String nom){
        this.name = nom;
        this.choice_pokemon = null;
    }

    /**
     * Retourne le nom du joueur.
     *
     * @return le nom du joueur
     */
    public String getNom() {
        return name;
    }

    /**
     * Permet au joueur de choisir un Pokémon à partir du Pokedex.
     *
     * <p>Cette méthode affiche la liste des Pokémon disponibles, demande à l'utilisateur
     * de saisir le numéro correspondant au Pokémon désiré, puis attribue ce Pokémon au joueur.
     * Le Pokémon choisi est retiré de la liste des Pokémon disponibles du Pokedex.</p>
     *
     * @param pokedex le Pokedex contenant la liste des Pokémon disponibles
     */
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
            System.out.println("Le Pokémon attribué à " + this.name + " est : " + choice_pokemon.getNom());
            System.out.println("\n---------------------------------------------------\n");
            pokemonListe.remove(choix);
        } else {
            System.out.println("Aucun Pokémon n'a été choisi.");
        }
    }

    /**
     * Retourne le Pokémon actuellement sélectionné par le joueur.
     *
     * @return le Pokémon choisi, ou {@code null} si aucun n'a été sélectionné
     */
    public Pokemon getPokemonChoisi() {
        return choice_pokemon;
    }

    /**
     * Permet au joueur d'effectuer jusqu'à trois tentatives pour sélectionner un Pokémon.
     *
     * <p>La méthode affiche le Pokedex, demande au joueur de choisir son Pokémon, puis vérifie
     * si le choix est valide. Si aucun Pokémon n'est sélectionné après trois essais, le programme se termine.</p>
     *
     * @param pokedex le Pokedex contenant la liste des Pokémon disponibles
     */
    public void essaiJoueur (Pokedex pokedex) {
        System.out.println(pokedex);
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
