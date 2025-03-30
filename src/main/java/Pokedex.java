import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;

public class Pokedex {
    public ArrayList<Pokemon> pokedex;
    private Integer maxPokedex = 6;

    public Pokedex(InputStream inputStream) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            pokedex = mapper.readValue(inputStream, Pokedex.class).getPokedex();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JsonCreator
    public Pokedex(@JsonProperty("pokedex") ArrayList<Pokemon> pokedex) {
        if (pokedex.size() > maxPokedex) {
            this.pokedex = new ArrayList<>(pokedex.subList(0, 6));
        } else {
            this.pokedex = pokedex;
        }
    }

    public ArrayList<Pokemon> getPokedex() {
        return pokedex;
    }

    @Override
    public String toString() {
        return "Pokedex : " + pokedex;
    }
}
