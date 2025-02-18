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

    @Override
    public String toString() {
        return "Pokedex : " + pokedex;
    }
}
