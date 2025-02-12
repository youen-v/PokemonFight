import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Map;

/**
 * La classe {@code Pokedex} représente la collection de tous les Pokémon disponibles dans l'application.
 *
 * <p>
 * Cette classe permet de gérer une liste de Pokémon. Elle offre notamment un constructeur annoté
 * pour la désérialisation JSON avec Jackson, une méthode pour récupérer la liste des Pokémon,
 * ainsi qu'une méthode pour ajouter un nouveau Pokémon à partir de ses caractéristiques.
 * </p>
 */
public class Pokedex {
    /**
     * La liste des Pokémon contenus dans le Pokedex.
     */
    public ArrayList<Pokemon> pokedex;

    /**
     * Constructeur par défaut.
     * Ce constructeur est utilisé notamment lors de la désérialisation lorsque Jackson a besoin
     * d'un constructeur sans argument.
     */
    public Pokedex() {}

    /**
     * Constructeur utilisé pour la désérialisation JSON.
     *
     * @param pokedex la liste des Pokémon à initialiser dans le Pokedex, extraite du JSON
     */
    @JsonCreator
    public Pokedex(@JsonProperty("pokedex") ArrayList<Pokemon> pokedex) {
        this.pokedex = pokedex;
    }

    /**
     * Retourne la liste des Pokémon contenus dans le Pokedex.
     *
     * @return une {@code ArrayList} contenant les Pokémon du Pokedex
     */
    public ArrayList<Pokemon> getPokedex() {
        return pokedex;
    }

    /**
     * Ajoute un nouveau Pokémon dans le Pokedex.
     *
     * <p>
     * Cette méthode instancie un nouveau Pokémon à partir des paramètres fournis et l'ajoute
     * à la liste des Pokémon.
     * </p>
     *
     * @param listeAttaque la liste des attaques du Pokémon, représentée par une {@code Map<String, Integer>}
     *                     où chaque clé est le nom de l'attaque et chaque valeur le dégât associé.
     * @param nom le nom du Pokémon
     * @param type le type du Pokémon (par exemple, "Eau", "Feu", "Plante", etc.)
     * @param pv les points de vie du Pokémon
     * @param defense la valeur de défense du Pokémon
     */
    public void setPokedex(Map<String, Integer> listeAttaque, String nom, String type, int pv, int defense) {
        // Instanciation et ajout des pokemons à la liste
        // Instanciation Pikachu

        Pokemon pokemon = new Pokemon(nom, type, pv, listeAttaque, defense);
        pokedex.add(pokemon);
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du Pokedex.
     *
     * @return une chaîne représentant le contenu du Pokedex
     */
    @Override
    public String toString() {
        return "Pokedex : " + pokedex;
    }
}
