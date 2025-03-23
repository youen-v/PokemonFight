import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class PokedexTest {
    @Test
    void testPokedex(){
        InputStream inputStream = PokedexTest.class.getResourceAsStream("/pokedex.json");
        Pokedex pokedex = new Pokedex(inputStream);
        System.out.println(pokedex);
        assert(true);
    }

    @Test
    void testPokedexException(){
        InputStream inputStream = PokedexTest.class.getResourceAsStream("/pokedex_exception.json");
        Pokedex pokedex = new Pokedex(inputStream);
        System.out.println(pokedex);
        assert(true);
    }
}
