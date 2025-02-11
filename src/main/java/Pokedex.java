import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pokedex {
    public List<Pokemon> pokedex;

    public Pokedex() {}

    @JsonCreator
    public Pokedex(@JsonProperty("pokedex") List<Pokemon> pokedex) {
        this.pokedex = pokedex;
    }

    public List<Pokemon> getPokedex() {
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
