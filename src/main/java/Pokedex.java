import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Map;

public class Pokedex {
    public ArrayList<Pokemon> pokedex;

    public Pokedex() {}

    @JsonCreator
    public Pokedex(@JsonProperty("pokedex") ArrayList<Pokemon> pokedex) {
        this.pokedex = pokedex;
    }

    public ArrayList<Pokemon> getPokedex() {
        return pokedex;
    }

    public void setPokedex(Map<String, Integer> listeAttaque, String nom, String type, int pv, int defense) {
        // Instanciation et ajout des pokemons à la liste
        // Instanciation Pikachu

        Pokemon pokemon = new Pokemon(nom, type, pv, listeAttaque, defense);
        pokedex.add(pokemon);
    }

    @Override
    public String toString() {
        return "Pokedex : " + pokedex;
    }
}
